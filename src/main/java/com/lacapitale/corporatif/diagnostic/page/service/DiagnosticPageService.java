package com.lacapitale.corporatif.diagnostic.page.service;

import com.lacapitale.corporatif.diagnostic.page.dao.DiagnosticPageImpl;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DiagnosticPageService {
    private RestTemplate restTemplate;
    private DiagnosticPageImpl diagnosticPageImpl;
    private DiagnosticPage diagnosticPage;

    public DiagnosticPageService() {
    }

    //Méthode pour les différents regexs
    public String[] getRegexForResponse(){

        String[] tabRegex = {"REGEX","FINDSTRING"};
        return tabRegex;
    }

    //Méthode qui match url http
    public boolean findMatchUrlHttpService(String url) {
        boolean matchUrl = false;
        Pattern pattern = Pattern.compile("http:.");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            if (matcher.group().trim().length() != 0) {
                matchUrl = true;
            }
        }
        return matchUrl;
    }

    //Méthode qui match url https
    public boolean findMatchUrlHttpsService(String url) {
        boolean matchUrl = false;
        Pattern pattern = Pattern.compile("https:.");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            if (matcher.group().trim().length() != 0) {
                matchUrl = true;
            }
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
        String resultRegexMatchValue = "";
            String validationResponse =
                    getStatusUrlHttpOrHttpsService(dp.getUrl()).toString();
            String patternString = dp.getValidationValue();
            Pattern pattern = Pattern.compile(patternString, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(validationResponse);
        while (matcher.find()) {
           // if (matcher.matches()) {
                resultRegexMatchValue = matcher.group();
           // }
        }
        return resultRegexMatchValue;
    }

    //Méthode pour matcher les validations value FindString des tests
    public String checkFindStringResponse(DiagnosticPage dp) {
        String retMatcher = "";
        String validationResponse =
                getStatusUrlHttpOrHttpsService(dp.getUrl()).toString();
        if(validationResponse.contains(dp.getValidationValue())){
            retMatcher = dp.getValidationValue();
        }
        return retMatcher;
    }

    //Méthode pour tester les validations Regex value des tests
    public boolean executeHealthToCheckService(DiagnosticPage diagnosticPage){
       //boolean boolValidationValue = false;

        if(diagnosticPage.getValidationType() == "REGEX"){
            checkRegexResponse(diagnosticPage);
            //boolValidationValue = diagnosticPage.getValidationStatusHealthCheck();
        }else if(diagnosticPage.getValidationType() == "FINDSTRING"){
            checkFindStringResponse(diagnosticPage);
        }
       // return boolValidationValue;
        return diagnosticPage.getValidationStatusHealthCheck();
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
        DiagnosticPageImpl diagnosticPage1 = new DiagnosticPageImpl();
        String url = diagnosticPage.getUrl();
        System.out.println(diagnosticPageService.checkRegexResponse(diagnosticPage));
        // System.out.println(diagnosticPageService.getStatusValidation());
        //System.out.println(diagnosticPageService.validateStatusResponse("FINDSTRING", "https://espaceconseiller.lacapitale.com/epargne/testSante"));
    }
}