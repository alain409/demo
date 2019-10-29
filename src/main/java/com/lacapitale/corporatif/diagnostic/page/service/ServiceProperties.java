package com.lacapitale.corporatif.diagnostic.page.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceProperties {

    // @Autowired
    //  ResourceLoader resourceLoader;
    public List<DiagnosticPage> getFileJson(String fileService){
        ObjectMapper mapper = new ObjectMapper();
        // DiagnosticPage valueResult = new DiagnosticPage();
        List<DiagnosticPage> listValueResult = new ArrayList<>();

    /*    String serviceListJson = "[\n" +
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
                "]";*/
        //Resource resource = resourceLoader.getResource("classpath:diagnosticpage.json");
        Resource resource = new ClassPathResource(fileService);
        try {
            InputStream input = resource.getInputStream();
            File serviceListJson = resource.getFile();
            List<DiagnosticPage> listDiagnosticPages =
                    mapper.readValue(new File(String.valueOf(serviceListJson)), new TypeReference<List<DiagnosticPage>>(){});
            for (DiagnosticPage diagnosticPage : listDiagnosticPages
            ) {
                listValueResult.add(diagnosticPage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listValueResult;
    }

    public  ServiceProperties()
    {
    }
}

