package com.lacapitale.corporatif.diagnostic.page.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import com.lacapitale.corporatif.diagnostic.page.service.ServiceProperties;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DiagnosticPageImpl implements DiagnosticPageDao {

    private ServiceProperties serviceProperties;
    private List<DiagnosticPage> listServicesProperties = getDataDiagPage();

    @Override
    public List<DiagnosticPage> getDataDiagPage() {
        serviceProperties = new ServiceProperties();
        List<DiagnosticPage> diagnosticPage = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<DiagnosticPage> diagnosticPageList = objectMapper
                    .readValue(new File(serviceProperties.getFileJson()),
                            new TypeReference<List<DiagnosticPage>>() {
                            });
            for (DiagnosticPage dp : diagnosticPageList
            ) {
                diagnosticPage = diagnosticPageList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diagnosticPage;
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
