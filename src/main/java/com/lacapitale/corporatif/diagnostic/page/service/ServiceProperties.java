package com.lacapitale.corporatif.diagnostic.page.service;

import org.springframework.beans.factory.annotation.Value;
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
    //Méthode pour récupérer un fichier du répertoire resources
  public String getFileJson(){

        Resource resource = new ClassPathResource("diagnosticpage.json");
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
