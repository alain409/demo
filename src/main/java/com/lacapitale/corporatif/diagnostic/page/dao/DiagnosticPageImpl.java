package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import com.lacapitale.corporatif.diagnostic.page.service.ServiceProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DiagnosticPageImpl implements DiagnosticPageDao {

    private ServiceProperties serviceProperties;
    private List<DiagnosticPage> listServicesProperties = getDataDiagPage();

    @Override
    public List<DiagnosticPage> getDataDiagPage() {
        serviceProperties = new ServiceProperties();
      return serviceProperties.getFileJson();
    }

    @Override
    public List<DiagnosticPage> findAll() {
        return listServicesProperties;
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
