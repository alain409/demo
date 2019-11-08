package com.lacapitale.corporatif.diagnostic.page.model;

import java.util.List;

public class DiagnosticPage {

    private List<String> listUrlsHealthCheckError;

    private String name;

    private String sector;

    private String division;

    private String type;

    private String url;

    private String healthtest;

    private String validationType;

    private String validationValue;

    private boolean validationStatusHealthCheck;

    private String validationStateHealthCheck;

    private StringBuffer response;

    public DiagnosticPage(List<String> urlsHealthCheckError, String name, String sector, String division,
                                String type, String url, String healthtest, String validationType,
                                 String validationValue, boolean validationStatusHealthCheck, String validationStateHealthCheck,
                                  StringBuffer response) {

        this.listUrlsHealthCheckError = urlsHealthCheckError;
        this.name = name;
        this.sector = sector;
        this.division = division;
        this.type = type;
        this.url = url;
        this.healthtest = healthtest;
        this.validationType = validationType;
        this.validationValue = validationValue;
        this.validationStatusHealthCheck = validationStatusHealthCheck;
        this.validationStateHealthCheck = validationStateHealthCheck;
        this.response = response;
    }

    public DiagnosticPage() {
    }

    public List<String> getListUrlsHealthCheckError() {
        return listUrlsHealthCheckError;
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

    public String getHealthtest() {
        return healthtest;
    }

    public String getValidationType() {
        return validationType;
    }

    public String getValidationValue() {
        return  validationValue;
    }

    public boolean getValidationStatusHealthCheck() {
        return validationStatusHealthCheck;
    }
/*  public int getCode() {
       diagnosticPageService = new DiagnosticPageService();
        int codeResponse = diagnosticPageService.getCodeResponseUrlHttpOrHttpsService(url);
        return codeResponse;
        return code;
    }*/
    public String getValidationStateHealthCheck(){return validationStateHealthCheck;}

    public StringBuffer getResponse() {
        return response;
    }

    public void setListUrlsHealthCheckError(List<String> listUrlsHealthCheckError) {
        this.listUrlsHealthCheckError = listUrlsHealthCheckError;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHealthtest(String healthtest) {
        this.healthtest = healthtest;
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
   public void setValidationStatusHealthCheck(boolean validationStatusHealthCheck) {
       this.validationStatusHealthCheck = validationStatusHealthCheck;
   }

   public void setValidationStateHealthCheck(String validationStateHealthCheck){
       this.validationStateHealthCheck = validationStateHealthCheck;
   }

    public void setResponse(StringBuffer response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "DiagnosticPage{" +
                "listUrlsHealthCheckError=" + listUrlsHealthCheckError +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", division='" + division + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", healthtest='" + healthtest + '\'' +
                ", validationType='" + validationType + '\'' +
                ", validationValue='" + validationValue + '\'' +
                ", validationStatusHealthCheck=" + validationStatusHealthCheck +
                ", validationStateHealthCheck='" + validationStateHealthCheck + '\'' +
                ", response=" + response +
                '}';
    }
}
