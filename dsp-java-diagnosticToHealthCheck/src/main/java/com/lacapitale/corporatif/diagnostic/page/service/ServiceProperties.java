package com.lacapitale.corporatif.diagnostic.page.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@Component("pageProperties")
public class ServiceProperties {

    public String getFileJson(){

        Resource resource = new ClassPathResource("template-file-diagnosticPage.json");
        File file = null;
        try {
           InputStream input = resource.getInputStream();
           file = resource.getFile();

       } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(file);
    }

    public ServiceProperties() {
    }

}
