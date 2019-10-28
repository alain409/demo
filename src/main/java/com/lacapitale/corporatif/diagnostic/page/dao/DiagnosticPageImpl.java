package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import com.lacapitale.corporatif.diagnostic.page.service.ServiceProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DiagnosticPageImpl implements DiagnosticPageDao {

    private static final String sectorCgen = "CGEN";
    private static final String sectorCaap = "CAAP";
    private ServiceProperties serviceProperties;
    private List<DiagnosticPage> listServicesProperties = getDataDiagPage();
    private Optional<DiagnosticPage> listServicesPropertiesCgen = getDataDiagPageCgen();
    private Optional<DiagnosticPage> listServicesPropertiesCaap = getDataDiagPageCaap();


   @Override
    public List<DiagnosticPage> getDataDiagPage() {
        serviceProperties = new ServiceProperties();
      return serviceProperties.getFileJson("diagnosticpage.json");
    }

    @Override
    public Optional<DiagnosticPage> getDataDiagPageCgen() {
       final Optional<DiagnosticPage> listSectorCgen = listServicesProperties.stream()
                            .filter(service -> service
                                    .getSector()
                                    .equals(sectorCgen))
                                    .findFirst();
       return listSectorCgen;
    }

    @Override
    public Optional<DiagnosticPage> getDataDiagPageCaap() {
        final Optional<DiagnosticPage> listSectorCaap = listServicesProperties.stream()
                .filter(service -> service
                        .getSector()
                        .equals(sectorCaap))
                        .findFirst();
        return listSectorCaap;
    }

    @Override
    public List<DiagnosticPage> findAll() {
        return listServicesProperties;
    }

    @Override
    public Optional<DiagnosticPage> findAllServicesCgen() {
        return listServicesPropertiesCgen;
    }

    @Override
    public Optional<DiagnosticPage> findAllServicesCaap() {
        return listServicesPropertiesCaap;
    }

   /* @Override
    public Optional<DiagnosticPage> findByEndPoint(String endPoint) {
        final Optional<DiagnosticPage> firstFindEndPoint = listServicesProperties.stream()
                .filter(service -> service.getEndPoint().equals(endPoint))
                .findFirst();
        return firstFindEndPoint;
    }*/

    @Override
    public Optional<DiagnosticPage> findByEndPointAdvisorSpace(String endPoint) {
        endPoint = "advisorSpace";
        String finalEndPoint = endPoint;
        final Optional<DiagnosticPage> firstFindEndPointAdvisorSpace = listServicesProperties.stream()
                .filter(service -> service.getEndPoint().equals(finalEndPoint))
                .findFirst();
        return firstFindEndPointAdvisorSpace;
    }

    @Override
    public Optional<DiagnosticPage> findByEndPointCapCourtier(String endPoint) {
        endPoint = "capCourtier";
        String finalEndPoint = endPoint;
        final Optional<DiagnosticPage> firstFindEndPointCapCourtier = listServicesProperties.stream()
                .filter(service -> service.getEndPoint().equals(finalEndPoint))
                .findFirst();
        return firstFindEndPointCapCourtier;
    }

    @Override
    public Optional<DiagnosticPage> findByEndPointPubPost(String endPoint) {
        endPoint = "pubPost";
        String finalEndPoint = endPoint;
        final Optional<DiagnosticPage> firstFindEndPointPubPost = listServicesProperties.stream()
                .filter(service -> service.getEndPoint().equals(finalEndPoint))
                .findFirst();
        return firstFindEndPointPubPost;
    }

    @Override
    public Optional<DiagnosticPage> findByEndPointEcEpargne(String endPoint) {
        endPoint = "ecEpargne";
        String finalEndPoint = endPoint;
        final Optional<DiagnosticPage> firstFindEndPointEcEpargne = listServicesProperties.stream()
                .filter(service -> service.getEndPoint().equals(finalEndPoint))
                .findFirst();
        return firstFindEndPointEcEpargne;
    }
}
