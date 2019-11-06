package com.lacapitale.corporatif.diagnostic.page.model;

import com.lacapitale.corporatif.diagnostic.page.service.DiagnosticPageService;
import com.lacapitale.corporatif.diagnostic.page.service.DiagnosticPageService;

public class DiagnosticPage {

    private String name;

    private String sector;

    private String division;

    private String type;

    private String url;

    private String healthtest;

    private String validationType;

    private String validationValue;

    private boolean validationStatusHealthCheck;

    private StringBuffer response;

    private DiagnosticPageService diagnosticPageService;

    public DiagnosticPage(String name, String sector, String division, String type, String url, String healthtest,
                          String validationType, String validationValue, boolean validationStatusHealthCheck, StringBuffer response) {
        this.healthtest = healthtest;
        this.name = name;
        this.sector = sector;
        this.division = division;
        this.type = type;
        this.url = url;
        this.healthtest = healthtest;
        this.validationType = validationType;
        this.validationValue = validationValue;
        this.validationStatusHealthCheck = validationStatusHealthCheck;
        this.response = response;
    }

    public DiagnosticPage() {
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }

    public String getDivision() {
        return division;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }


    public String getValidationType() {
        //diagnosticPageService = new DiagnosticPageService();
        //String statusResponse = diagnosticPageService.validateStatusResponse()
        //  return statusResponse;
        return validationType;
    }

    public String getValidationValue() {
      /* diagnosticPageService = new DiagnosticPageService();
        String codeResponse = diagnosticPageService.validateStatusResponse(validationType,url);
        return codeResponse;*/
       return  validationValue;
    }

    public boolean getValidationStatusHealthCheck() {
       /* diagnosticPageService = new DiagnosticPageService();
        boolean boolValidation = diagnosticPageService.validateBooleanStatusResponse(validationType,url);
        return boolValidation;*/
       return validationStatusHealthCheck;
    }
/*  public int getCode() {
       diagnosticPageService = new DiagnosticPageService();
        int codeResponse = diagnosticPageService.getCodeResponseUrlHttpOrHttpsService(url);
        return codeResponse;
        return code;
    }*/

    public StringBuffer getResponse() {
        return response;
    }

    public String getHealthtest() {
        return healthtest;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValidationStatusHealthCheck(boolean validationStatusHealthCheck) {
        this.validationStatusHealthCheck = validationStatusHealthCheck;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    public void setValidationValue(String validationValue) {
        this.validationValue = validationValue;
    }

   /* public void setCode(int code) {
        this.code = code;
    }*/

    public void setResponse(StringBuffer response) {
        this.response = response;
    }

    public void setHealthtest(String healthtest) {
        this.healthtest = healthtest;
    }

    @Override
    public String toString() {
        return "DiagnosticPage{" +
                "name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", division='" + division + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", healthtest='" + healthtest + '\'' +
                ", validationType='" + validationType + '\'' +
                ", validationValue='" + validationValue + '\'' +
                ", validationStatusHealthCheck='" + validationStatusHealthCheck + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
