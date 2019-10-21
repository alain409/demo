package com.lacapitale.corporatif.diagnostic.page.model;

import com.lacapitale.corporatif.diagnostic.page.dao.DiagnosticPageImpl;
import com.lacapitale.corporatif.diagnostic.page.service.DiagnosticPageService;

public class DiagnosticPage {

    private String name;

    private String sector;

    private String type;

    private String url;

    private String endPoint;

    private int code;

    private String status;

    private DiagnosticPageImpl diagnosticPageImp;

    private DiagnosticPageService diagnosticPageService;

    public DiagnosticPage( String name, String sector, String type, String url, String endPoint, int code, String status) {
        this.endPoint = endPoint;
        this.name = name;
        this.sector = sector;
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

    public String getEndPoint() {
        return endPoint;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSector(String sector) {
        this.sector = sector;
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

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "DiagnosticPage{" +
                "name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", code=" + code +
                ", status='" + status + '\'' +
                '}';
    }
}
