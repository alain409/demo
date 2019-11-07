package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;

import java.util.List;

public interface DiagnosticPageDao {

    public List<DiagnosticPage> getDataDiagPageNoResponse();

    public List<DiagnosticPage> getDataDiagPageResponse();

    public List<DiagnosticPage> findAllWithNoResponse();

    public List<DiagnosticPage> findAllWithResponse();

    public List<DiagnosticPage> getListShowResponse(boolean showResponse);

    public List<DiagnosticPage> findAllByAllServicesNoOrResponse(String sector, String division,String healthtest, boolean showResponse);

   public List<DiagnosticPage> findAllServicesBySectorNoOrResponse(String sector, boolean showResponse);

   public List<DiagnosticPage> findAllServicesBySectorDivisionNoOrResponse(String sector, String division, boolean showResponse);

}
