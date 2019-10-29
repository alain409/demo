package com.lacapitale.corporatif.diagnostic.page.model;

import com.lacapitale.corporatif.diagnostic.page.dao.DiagnosticPageImpl;
import com.lacapitale.corporatif.diagnostic.page.service.DiagnosticPageService;

public class DiagnosticPage {

    private String name;

    private String sector;

    private String division;

    private String type;

    private String url;

    private String healthtest;

    private int code;

    private String status;

    private DiagnosticPageImpl diagnosticPageImp;

    private DiagnosticPageService diagnosticPageService;

    public DiagnosticPage( String name, String sector, String division, String type, String url, String healthtest, int code, String status) {
        this.healthtest = healthtest;
        this.name = name;
        this.sector = sector;
        this.division = division;
        this.type = type;
        this.url = url;
        this.code = code;
        this.status = status;
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

    public int getCode() {
        diagnosticPageService = new DiagnosticPageService();
        int codeResponse = diagnosticPageService.getCodeResponseUrlHttpOrHttpsService(url);
        return codeResponse;
    }

    public StringBuffer getStatus() {
        diagnosticPageService = new DiagnosticPageService();
        StringBuffer response = diagnosticPageService.getStatusUrlHttpOrHttpsService(url);
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", code=" + code +
                ", status='" + status + '\'' +
                '}';
    }
}
