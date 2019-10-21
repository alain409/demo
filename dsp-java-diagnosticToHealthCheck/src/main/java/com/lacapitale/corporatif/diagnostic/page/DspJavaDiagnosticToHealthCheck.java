package com.lacapitale.corporatif.diagnostic.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DspJavaDiagnosticToHealthCheck {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(DspJavaDiagnosticToHealthCheck.class, args);
	}
}
