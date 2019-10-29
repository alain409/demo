package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;

import java.util.List;

public interface DiagnosticPageDao {

    public List<DiagnosticPage> getDataDiagPage();

    public List<DiagnosticPage> findAll();

    public List<DiagnosticPage> findAllByAllServices(String sector, String division,String healthtest);

    public List<DiagnosticPage> finddAllServicesBySector(String sector);

    public List<DiagnosticPage> findAllServicesBySectorDivision(String sector, String division);

}
