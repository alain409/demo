package com.lacapitale.corporatif.diagnostic.page;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticPageConfig {
    private static String diagnosticPageFilename;

    @Value("${diagnosticpage.filename}")
    public String setDiagnosticPageFilename(String value) {
        return this.diagnosticPageFilename = value;
    }

    public String getDiagnosticPageFilename() {
        return this.diagnosticPageFilename;
    }
}