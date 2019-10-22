package com.lacapitale.corporatif.diagnostic.page.service;

import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DiagnosticPageService {

    public DiagnosticPageService() {
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

  //Pour obtenir un code de réponse pour la requête Http
    public int getCodeResponseUrlHttpOrHttpsService(String url) {
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
    }
}