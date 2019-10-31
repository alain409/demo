package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import com.lacapitale.corporatif.diagnostic.page.service.DiagnosticPageService;
import com.lacapitale.corporatif.diagnostic.page.service.ServiceProperties;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DiagnosticPageImpl implements DiagnosticPageDao {

    private ServiceProperties serviceProperties;
    DiagnosticPageService diagnosticPageService;

    @Override
    public List<DiagnosticPage> getDataDiagPageNoResponse() {
        serviceProperties = new ServiceProperties();
        // return serviceProperties.getFileJson("diagnosticpage.json");
        return serviceProperties.getFileJson();
    }

    @Override
    public List<DiagnosticPage> findAll() {
        return getDataDiagPageNoResponse();
    }

    @Override
    public List<DiagnosticPage> getDataDiagPageResponse() {
        List<DiagnosticPage> listValueResult = new ArrayList<>();
        diagnosticPageService = new DiagnosticPageService();
        for (DiagnosticPage dp : getDataDiagPageNoResponse()
        ) {
            DiagnosticPage diagnosticPage = new DiagnosticPage(
                    dp.getName(), dp.getSector(),
                    dp.getDivision(), dp.getType(),
                    dp.getUrl(), dp.getHealthtest(), dp.getCode(),
                    diagnosticPageService.getResponseUrlHttpOrHttpsService(dp.getUrl()));
            listValueResult.add(diagnosticPage);
        }
        return listValueResult;
    }

    @Override
    public List<DiagnosticPage> getListShowReponse(boolean showResponse) {
        List<DiagnosticPage> listAllHealthtestNoOrResponse = null;
        if (showResponse) {
            listAllHealthtestNoOrResponse = getDataDiagPageResponse();
        } else {
            listAllHealthtestNoOrResponse = getDataDiagPageNoResponse();
        }
        return listAllHealthtestNoOrResponse;
    }

    @Override
    public List<DiagnosticPage> findAllByAllServicesNoOrResponse(String sector, String division, String healthtest, boolean showResponse) {
        final List<DiagnosticPage> listAllHealthtest = getListShowReponse(showResponse).stream()
                .filter(service -> service
                        .getSector()
                        .equals(sector) &&
                        service
                                .getDivision()
                                .equals(division) &&
                        service
                                .getHealthtest()
                                .equals(healthtest))
                .collect(Collectors.toList());
        return listAllHealthtest;
    }

    @Override
    public List<DiagnosticPage> findAllServicesBySectorNoOrResponse(String sector, boolean showResponse) {
        final List<DiagnosticPage> listAllSector = getListShowReponse(showResponse).stream()
                .filter(service -> service
                        .getSector()
                        .equals(sector))
                .collect(Collectors.toList());
        return listAllSector;
    }

    @Override
    public List<DiagnosticPage> findAllServicesBySectorDivisionNoOrResponse(String sector, String division, boolean showResponse) {
        final List<DiagnosticPage> listAllDivision = getListShowReponse(showResponse).stream()
                .filter(service -> service
                        .getSector()
                        .equals(sector) &&
                        service
                                .getDivision()
                                .equals(division))
                .collect(Collectors.toList());
        return listAllDivision;
    }
}
