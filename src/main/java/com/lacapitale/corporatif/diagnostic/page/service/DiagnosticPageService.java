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

    //Cette méthode va éguiller les valeurs de validation type
    public List<String> redirectValidationType(String valueValidationType)
    {
        diagnosticPageImpl = new DiagnosticPageImpl();
        List<DiagnosticPage> listAllValidationType = null;
        List<DiagnosticPage> listDiagnostic = new ArrayList<>();
        List<String> tabDiagnostic = new ArrayList<>();
        switch(valueValidationType) {
            case "REGEX":
                listAllValidationType = diagnosticPageImpl.getDataDiagPageNoResponse().stream()
                        .filter(service -> service
                                .getValidationType()
                                .equals("REGEX"))
                        .collect(Collectors.toList());
                break;
            case "FINDSTRING":
                listAllValidationType = diagnosticPageImpl.getDataDiagPageNoResponse().stream()
                        .filter(service -> service
                                .getValidationType()
                                .equals("FINDSTRING"))
                        .collect(Collectors.toList());
                break;
            default:
                break;
        }

        for (DiagnosticPage dp : listAllValidationType
        ) {
            DiagnosticPage diagnosticPage = new DiagnosticPage(
                    dp.getName(), dp.getSector(),
                    dp.getDivision(), dp.getType(),
                    dp.getUrl(), dp.getHealthtest(),
                    dp.getValidationType(),
                    dp.getValidationValue(),
                    dp.getValidationStatusHealthCheck(),
                    dp.getResponse());
            tabDiagnostic.add(dp.getValidationValue());
            listDiagnostic.add(dp);
        }
        return tabDiagnostic;
    }

    //Méthode retourner une réponse à un url
  /*  public final String getResponseUrlService(String url){
        diagnosticPageImpl = new DiagnosticPageImpl();
        url = listToValidateResponse().getUrl();
        restTemplate = new RestTemplate();
        final String response = restTemplate.getForObject(url, String.class);
        return response;
    }*/

    //Méthode pour valider le statut de la réponse
    public String validateStatusResponse(String validationType, String url) {
        String retMatcher = "";
        for (String str : redirectValidationType(validationType)
        ) {
            Pattern pattern = Pattern.compile(str, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(getStatusUrlHttpOrHttpsService(url));
            while (matcher.find()) {
               if (matcher.group().length() != 0) {
                    retMatcher = matcher.group().trim();
                }
            }
        }
        return retMatcher;
    }

    //Méthode pour retourner vrai ou faux si le regex match
    public boolean validateBooleanStatusResponse(String validationType, String url) {
        boolean retMatcher = false;
        for (String str : redirectValidationType(validationType)
        ) {
            Pattern pattern = Pattern.compile(str, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(getStatusUrlHttpOrHttpsService(url));
           while (matcher.find()) {
                if (str == "<.+icon-echec.+>") {
                    retMatcher = false;
                }else{
                    retMatcher = true;
                }
           }
        }
        return retMatcher;
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

    //Méthode pour valider le code de la réponse
  /*  public int validateCode200Response(String url){
        String retMatcher = "";
        int code = 0;
        diagnosticPageImpl = new DiagnosticPageImpl();
        for (DiagnosticPage dp : diagnosticPageImpl.getDataDiagPageNoResponse()
        ) {
            DiagnosticPage diagnosticPage = new DiagnosticPage(
                    dp.getName(), dp.getSector(),
                    dp.getDivision(), dp.getType(),
                    dp.getUrl(), dp.getHealthtest(),
                    dp.getValidationType(),
                    dp.getValidationValue(),
                    dp.getResponse());
            code = dp.getValidationValue();
            url = dp.getUrl();
        }
        Pattern pattern = Pattern.compile(String.valueOf(code));
        Matcher matcher = pattern.matcher(getResponseUrlService(url));
        while(matcher.find()){
            if(matcher.group().length() != 0){
                retMatcher = matcher.group().trim();
            }
        }
        return Integer.parseInt(retMatcher);
    }*/

    public static void main(String[] args) {
        DiagnosticPageService diagnosticPageService = new DiagnosticPageService();
        DiagnosticPage diagnosticPage = new DiagnosticPage();
        DiagnosticPageImpl diagnosticPage1 = new DiagnosticPageImpl();
        String url = diagnosticPage.getUrl();
        // System.out.println(diagnosticPageService.validateStatusResponse("https://espaceconseiller.lacapitale.com/espaceconseiller/test-sante"));
        // System.out.println(diagnosticPageService.getStatusValidation());
        //System.out.println(diagnosticPageService.validateStatusResponse("FINDSTRING", "https://espaceconseiller.lacapitale.com/epargne/testSante"));
    }
}