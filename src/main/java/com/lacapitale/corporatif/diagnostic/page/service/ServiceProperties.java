package com.lacapitale.corporatif.diagnostic.page.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacapitale.corporatif.diagnostic.page.com.lacapitale.corporatif.diagnostic.page.config.DiagnosticPageConfig;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceProperties {
    private static final Logger logger = LoggerFactory.getLogger(ServiceProperties.class);
    // @Autowired
    //  ResourceLoader resourceLoader;
    public List<DiagnosticPage> getFileJson(String fileService){
        ObjectMapper mapper = new ObjectMapper();
        // DiagnosticPage valueResult = new DiagnosticPage();
        List<DiagnosticPage> listValueResult = new ArrayList<>();


        //Resource resource = resourceLoader.getResource("classpath:diagnosticpage.json");
       // Resource resource = new ClassPathResource(fileService);
        try {
            DiagnosticPageConfig config = new DiagnosticPageConfig();

            String diagnosticPageFilename = config.getDiagnosticPageFilename();

            //logger.info("nom du répertoire : " + diagnosticPageFilename.toString());

            ResourceLoader resourceLoader = new DefaultResourceLoader();

            Resource resource = resourceLoader.getResource(diagnosticPageFilename);

            File file = resource.getFile();

            List<DiagnosticPage> listDiagnosticPages =
                    mapper.readValue(new File(String.valueOf(file)), new TypeReference<List<DiagnosticPage>>(){});

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

