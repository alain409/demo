package com.lacapitale.corporatif.diagnostic.page.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Component("pageProperties")
public class ServiceProperties {

    //Méthode pour récupérer un fichier du répertoire resources

    public String getFileJson(){


        //String fileName = "diagnosticpage.json";
        String fileName = "application.properties";

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        return String.valueOf(file);
    }

/*
  public List<DiagnosticPage> getFileJson(){
      ObjectMapper mapper = new ObjectMapper();
      DiagnosticPage valueResult = new DiagnosticPage();
      List<DiagnosticPage> listValueResult = new ArrayList<>();

      //String serviceListJson = "[{\"name\": \"espaceConseiller\",\"sector\": \"Assurances individuelles\",\"type\": \"Diagnostic\",\"url\": \"https://espaceconseiller.lacapitale.com/espaceconseiller/test-sante\",\"endPoint\": \"advisorSpace\"},{\"name\": \"cap-courtier\",\"sector\": \"Assurances individuelles\",\"type\": \"Diagnostic\",\"url\": \"https://services.lacapitale.com/cap-courtier/nagios\",\"endPoint\": \"capCourtier\"}, {\"name\": \"svc-corp-pubpost\",\"sector\": \"Services corporatifs\",\"type\": \"Diagnostic\",\"url\": \"http://svc-corp-pubpost.capitale.qc.ca/publipostage/health\",\"endPoint\": \"pubPost\"}, {\"name\": \"ec-epargne\",\"sector\": \"Services individuelles\",\"type\": \"Diagnostic\",\"url\": \"https://espaceconseiller.lacapitale.com/epargne/testSante\",\"endPoint\": \"ecEpargne\"}]";
      String serviceListJson = "[\n" +
              "  {\n" +
              "    \"name\": \"espaceConseiller\",\n" +
              "    \"sector\": \"Assurances individuelles\",\n" +
              "    \"type\": \"Diagnostic\",\n" +
              "    \"url\": \"https://espaceconseiller.lacapitale.com/espaceconseiller/test-sante\",\n" +
              "    \"endPoint\": \"advisorSpace\"\n" +
              "  },\n" +
              "  {\n" +
              "    \"name\": \"cap-courtier\",\n" +
              "    \"sector\": \"Assurances individuelles\",\n" +
              "    \"type\": \"Diagnostic\",\n" +
              "    \"url\": \"https://services.lacapitale.com/cap-courtier/nagios\",\n" +
              "    \"endPoint\": \"capCourtier\"\n" +
              "  },\n" +
              "  {\n" +
              "    \"name\": \"svc-corp-pubpost\",\n" +
              "    \"sector\": \"Services corporatifs\",\n" +
              "    \"type\": \"Diagnostic\",\n" +
              "    \"url\": \"http://svc-corp-pubpost.capitale.qc.ca/publipostage/health\",\n" +
              "    \"endPoint\": \"pubPost\"\n" +
              "  },\n" +
              "  {\n" +
              "    \"name\": \"ec-epargne\",\n" +
              "    \"sector\": \"Services individuelles\",\n" +
              "    \"type\": \"Diagnostic\",\n" +
              "    \"url\": \"https://espaceconseiller.lacapitale.com/epargne/testSante\",\n" +
              "    \"endPoint\": \"ecEpargne\"\n" +
              "  }\n" +
              "]";
        try {
            List<DiagnosticPage> listDiagnosticPages =
                                mapper.readValue(new String(serviceListJson), new TypeReference<List<DiagnosticPage>>(){});
            for (DiagnosticPage diagnosticPage : listDiagnosticPages
                 ) {
                listValueResult.add(diagnosticPage);
            }
       } catch (IOException e) {
            e.printStackTrace();
        }
        return listValueResult;
    }
*/

    public ServiceProperties() {
    }

    public static void main(String[] args) {
        ServiceProperties serviceProperties = new ServiceProperties();
        System.out.println(serviceProperties.getFileJson());
    }
}
