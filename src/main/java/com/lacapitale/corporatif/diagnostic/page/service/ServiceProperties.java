package com.lacapitale.corporatif.diagnostic.page.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacapitale.corporatif.diagnostic.page.DiagnosticPageConfig;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ServiceProperties {
    private static final Logger logger = LoggerFactory.getLogger(ServiceProperties.class);
    private DiagnosticPageConfig config;

     @Autowired
     private ResourceLoader resourceLoader;

    public List<DiagnosticPage> getFileJson(){
        ObjectMapper mapper = new ObjectMapper();

        config = new DiagnosticPageConfig();

        String diagnosticPageFilename = config.getDiagnosticPageFilename();

        ResourceLoader resourceLoader = new DefaultResourceLoader();

        List<DiagnosticPage> listValueResult = new ArrayList<>();

        Resource resource = resourceLoader.getResource(diagnosticPageFilename);

        logger.info("nom du répertoire : " + diagnosticPageFilename);

        try {

            File fileInputStream = resource.getFile();
            List<DiagnosticPage> listDiagnosticPages =
                    mapper.readValue(new FileInputStream(String.valueOf(fileInputStream)), new TypeReference<List<DiagnosticPage>>(){});

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

