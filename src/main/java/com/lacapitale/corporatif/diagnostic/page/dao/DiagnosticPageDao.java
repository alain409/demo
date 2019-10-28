package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;

import java.util.List;
import java.util.Optional;

//Ici on présente toutes les méthodes qui permettent l'accès aux BD

public interface DiagnosticPageDao {

    public List<DiagnosticPage> getDataDiagPage();

    public Optional<DiagnosticPage> getDataDiagPageCgen();

    public Optional<DiagnosticPage> getDataDiagPageCaap();

    public List<DiagnosticPage> findAll();

    public Optional<DiagnosticPage> findAllServicesCgen();

    public Optional<DiagnosticPage> findAllServicesCaap();

   // public Optional<DiagnosticPage> findByEndPoint(String endPoint);

    public Optional<DiagnosticPage> findByEndPointAdvisorSpace(String endPoint);

    public Optional<DiagnosticPage> findByEndPointCapCourtier(String endPoint);

    public Optional<DiagnosticPage> findByEndPointPubPost(String endPoint);

    public Optional<DiagnosticPage> findByEndPointEcEpargne(String endPoint);

}
