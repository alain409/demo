package com.lacapitale.corporatif.diagnostic.page.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.Transient;
import java.util.List;

public class DiagnosticPage {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String resultDiagnosticHealthCheck;

    //@JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> listUrlsHealthCheckError;

   @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sector;

   @JsonInclude(JsonInclude.Include.NON_NULL)
    private String division;

   @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String healthtest;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String validationType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String validationValue;

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean validationStatusHealthCheck;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String validationStateHealthCheck;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StringBuffer response;

    public DiagnosticPage(String name, String sector, String division, String type, String url, String healthtest,
                          String validationType, String validationValue, boolean validationStatusHealthCheck,
                          String validationStateHealthCheck, StringBuffer response) {
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

    public DiagnosticPage(String resultDiagnosticHealthCheck, List<String> listUrlsHealthCheckError/*,String name,
                          @JsonInclude(JsonInclude.Include.NON_NULL) String sector, @JsonInclude(JsonInclude.Include.NON_NULL) String division,
                          @JsonInclude(JsonInclude.Include.NON_NULL) String type, @JsonInclude(JsonInclude.Include.NON_NULL) String url,
                          @JsonInclude(JsonInclude.Include.NON_NULL) String healthtest,
                          @JsonInclude(JsonInclude.Include.NON_NULL) String validationType, @JsonInclude(JsonInclude.Include.NON_NULL) String validationValue,
                          @JsonInclude(JsonInclude.Include.NON_NULL) boolean validationStatusHealthCheck,
                          @JsonInclude(JsonInclude.Include.NON_NULL) String validationStateHealthCheck,
                          @JsonInclude(JsonInclude.Include.NON_NULL)StringBuffer response*/) {
        this.resultDiagnosticHealthCheck = resultDiagnosticHealthCheck;
        this.listUrlsHealthCheckError = listUrlsHealthCheckError;
    }

    public DiagnosticPage() {
    }

    public String getResultDiagnosticHealthCheck() {
        return resultDiagnosticHealthCheck;
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
    public String getValidationStateHealthCheck(){
        return validationStateHealthCheck;
    }

    public StringBuffer getResponse() {
        return response;
    }

    public void setResultDiagnosticHealthCheck(String resultDiagnosticHealthCheck) {
        this.resultDiagnosticHealthCheck = resultDiagnosticHealthCheck;
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
