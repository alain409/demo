package com.lacapitale.corporatif.diagnostic.page.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacapitale.corporatif.diagnostic.page.DiagnosticPageConfig;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // @Autowired
    //  ResourceLoader resourceLoader;
   // public List<DiagnosticPage> getFileJson(String fileService){
    public List<DiagnosticPage> getFileJson(){
        ObjectMapper mapper = new ObjectMapper();
        // DiagnosticPage valueResult = new DiagnosticPage();
        List<DiagnosticPage> listValueResult = new ArrayList<>();


        //Resource resource = resourceLoader.getResource("classpath:diagnosticpage.json");
       // Resource resource = new ClassPathResource(fileService);
        try {

            List<DiagnosticPage> listDiagnosticPages =
                    mapper.readValue(new FileInputStream("diagnosticpage.json"), new TypeReference<List<DiagnosticPage>>(){});


           /* DiagnosticPageConfig config = new DiagnosticPageConfig();

            String diagnosticPageFilename = config.getDiagnosticPageFilename();

            logger.info("nom du répertoire : " + diagnosticPageFilename);

            ResourceLoader resourceLoader = new DefaultResourceLoader();

            Resource resource = resourceLoader.getResource(diagnosticPageFilename);

            File file = resource.getFile();

            List<DiagnosticPage> listDiagnosticPages =
                    mapper.readValue(new File(String.valueOf(file)), new TypeReference<List<DiagnosticPage>>(){});
*/
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

