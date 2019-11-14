package com.lacapitale.corporatif.diagnostic.page.service;

import com.lacapitale.corporatif.diagnostic.page.dao.DiagnosticPageImpl;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DiagnosticPageService {
    private RestTemplate restTemplate;
    private DiagnosticPageImpl diagnosticPageImpl;
    private DiagnosticPage diagnosticPage;

    public DiagnosticPageService() {
    }

    //Méthode qui match url http
    public boolean findMatchUrlHttpService(String url) {
        boolean matchUrl = false;
        Pattern pattern = Pattern.compile("^(http:\\/\\/www\\.|http:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            //  if (matcher.group().trim().length() != 0) {
            matchUrl = true;
            //  }
        }
        return matchUrl;
    }

    //Méthode qui match url https
    public boolean findMatchUrlHttpsService(String url) {
        boolean matchUrl = false;
        Pattern pattern = Pattern.compile("^(https:\\/\\/www\\.|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            // if (matcher.group().trim().length() != 0) {
            matchUrl = true;
            // }
        }
        return matchUrl;
    }

    //Méthode pour les requêtes Http
    public StringBuffer getStatusHttpService(String url) throws Exception {
        StringBuffer response = new StringBuffer();
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response;
        }
    }

    //Méthode pour les requêtes Https
    public StringBuffer getStatusHttpsService(String url) throws Exception {
        StringBuffer response = new StringBuffer();
        HttpsURLConnection httpsClient =
                (HttpsURLConnection) new URL(url).openConnection();
        httpsClient.setRequestMethod("GET");
        httpsClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(httpsClient.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return  response;
        }
    }

    //Méthode pour retourner le statut Http ou Https
    public StringBuffer getStatusUrlHttpOrHttpsService(String url) {
        StringBuffer response = new StringBuffer();
        try {
            if (findMatchUrlHttpService(url)) {
                response = getStatusHttpService(url);
            } else if (findMatchUrlHttpsService(url)) {
                response = getStatusHttpsService(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    //Méthode pour matcher les validations value Regex  des tests
    public String checkRegexResponse(DiagnosticPage dp){
        String stringRegexMatchValue = "";
        String validationResponse =
                getStatusUrlHttpOrHttpsService(dp.getUrl()).toString();
        Pattern pattern = Pattern.compile(dp.getValidationValue(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(validationResponse);
        while (matcher.find()) {
            // if (matcher.matches()) {
            stringRegexMatchValue = matcher.group().trim();
            // }
        }
        return stringRegexMatchValue;
    }

    //Méthode pour matcher les validations value FindString des tests
    public String checkFindStringResponse(DiagnosticPage dp) {
        String stringFindStringMatchValue = "";
        String validationResponse =
                getStatusUrlHttpOrHttpsService(dp.getUrl()).toString();
        if(validationResponse.contains(dp.getValidationValue())){
            stringFindStringMatchValue = dp.getValidationValue();
        }
        return stringFindStringMatchValue;
    }

    //Pour obtenir un code de réponse pour la requête Http
    public String checkHttpCodeCodeResponse(DiagnosticPage dp) {
        int code = 0;
        try {
            if (findMatchUrlHttpService(dp.getUrl())) {
                HttpURLConnection httpClient =
                        (HttpURLConnection) new URL(dp.getUrl()).openConnection();
                if(dp.getValidationValue().equalsIgnoreCase(String.valueOf(httpClient.getResponseCode())))
                code = httpClient.getResponseCode();
            } else if (findMatchUrlHttpsService(dp.getUrl())) {
                HttpsURLConnection httpsClient =
                        (HttpsURLConnection) new URL(dp.getUrl()).openConnection();
                if(dp.getValidationValue().equalsIgnoreCase(String.valueOf(httpsClient.getResponseCode())))
                code = httpsClient.getResponseCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(code);
    }

    //Méthode pour obtenir validationStatusHealthCheck (un booléen sur tests)
    public boolean executeHealthToCheckService(DiagnosticPage diagnosticPage){
        boolean boolValidationValue = false;
        if(checkRegexResponse(diagnosticPage) != null ){
            boolValidationValue = true;
        }else if(checkFindStringResponse(diagnosticPage) != null){
            boolValidationValue = true;
        }else if(checkHttpCodeCodeResponse(diagnosticPage) != null){
            boolValidationValue = true;
        }
        return boolValidationValue;
    }

    //Méthode pour désigner quelle action à appliquer sur les validations type pour obtenir les validations values
    public String getMatchValidationValue(DiagnosticPage diagnosticPage){
        String resultValidationValue = "";
        if(diagnosticPage.getValidationType().equalsIgnoreCase("REGEX")){
            resultValidationValue = checkRegexResponse(diagnosticPage);
        }else if(diagnosticPage.getValidationType().equalsIgnoreCase("FINDSTRING")){
            resultValidationValue = checkFindStringResponse(diagnosticPage);
        }else if(diagnosticPage.getValidationType().equalsIgnoreCase("HTTPCODE")){
            resultValidationValue = checkHttpCodeCodeResponse(diagnosticPage);
        }
        return resultValidationValue;
    }

    //Méthode remplir la liste des url des tests en fail
  public List<String> getListUrlsHealthCheckWithResponseError() {
         DiagnosticPageImpl diagnosticPageImpl = new DiagnosticPageImpl();
        List<String> listUrlsWithResponseError = new ArrayList<>();
        String urlError = "";
        for (DiagnosticPage dp : diagnosticPageImpl.getAllServicesWithResponse()) {
            if(dp.getValidationStateHealthCheck().equalsIgnoreCase("ERROR")){
                urlError = new String(dp.getUrl());
                listUrlsWithResponseError.add(urlError);
            }
        }
        return listUrlsWithResponseError;
    }

    public List<String> getListUrlsHealthCheckNoResponseError() {
        DiagnosticPageImpl diagnosticPageImpl = new DiagnosticPageImpl();
        List<String> listUrlsNoResponseError = new ArrayList<>();
        String urlError = "";
        for (DiagnosticPage dp : diagnosticPageImpl.getAllServicesNoResponse()) {
            if(dp.getValidationStateHealthCheck().equalsIgnoreCase("ERROR")){
                urlError = new String(dp.getUrl());
                listUrlsNoResponseError.add(urlError);
            }
        }
        return listUrlsNoResponseError;
    }

    public String getStatusResultDiagnosticHealthCheck(){
        if((!getListUrlsHealthCheckWithResponseError().isEmpty()) ||
                (!getListUrlsHealthCheckNoResponseError().isEmpty()))
            return "FAIL";
        return "SUCCES";
    }

    //Pour obtenir un code de réponse pour la requête Http
   /* public int getCodeResponseUrlHttpOrHttpsService(String url) {
        int code = 0;
        try {
            if (findMatchUrlHttpService(url)) {
                HttpURLConnection httpClient =
                        (HttpURLConnection) new URL(url).openConnection();
                code = httpClient.getResponseCode();
            } else if (findMatchUrlHttpsService(url)) {
                HttpsURLConnection httpsClient =
                        (HttpsURLConnection) new URL(url).openConnection();
                code = httpsClient.getResponseCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }*/

    public static void main(String[] args) {
        DiagnosticPageService diagnosticPageService = new DiagnosticPageService();
        DiagnosticPage diagnosticPage = new DiagnosticPage();
 //System.out.println(diagnosticPageService.getCodeResponseUrlHttpOrHttpsService("https://espaceconseiller.lacapitale.com/espaceconseiller/test-sante"));

        // DiagnosticPageImpl diagnosticPage1 = new DiagnosticPageImpl();
        //  String url = diagnosticPage.getUrl();
        //   System.out.println(diagnosticPageService.checkRegexResponse(diagnosticPage));
        // System.out.println(diagnosticPageService.getStatusValidation());
        //System.out.println(diagnosticPageService.validateStatusResponse("FINDSTRING", "https://espaceconseiller.lacapitale.com/epargne/testSante"));
    }
}