package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import com.lacapitale.corporatif.diagnostic.page.service.DiagnosticPageService;
import com.lacapitale.corporatif.diagnostic.page.service.ServiceProperties;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DiagnosticPageImpl implements com.lacapitale.corporatif.diagnostic.page.dao.DiagnosticPageDao {

    private ServiceProperties serviceProperties;
    DiagnosticPageService diagnosticPageService;
    DiagnosticPage diagnosticPage;

    @Override
    public List<DiagnosticPage> getDataDiagPageNoResponse() {
        serviceProperties = new ServiceProperties();
        return serviceProperties.getFileJson();
    }

    @Override
    public List<DiagnosticPage> findAllWithNoResponse() {
        return getDataDiagPageNoResponse();
    }

    @Override
    public List<DiagnosticPage> findAllWithResponse() {
        return getDataDiagPageResponse();
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
                    dp.getUrl(), dp.getHealthtest(),
                    dp.getValidationType(),
                    diagnosticPageService.checkRegexResponse(dp),
                    diagnosticPageService.executeHealthToCheckService(dp),
                    diagnosticPageService.getStatusUrlHttpOrHttpsService(dp.getUrl()));
            listValueResult.add(diagnosticPage);
        }
        return listValueResult;
    }

    @Override
    public List<DiagnosticPage> getListShowResponse(boolean showResponse) {
        if (showResponse)
          return findAllWithResponse();
        return findAllWithNoResponse();
    }

    @Override
    public List<DiagnosticPage> findAllByAllServicesNoOrResponse(String sector, String division, String healthtest, boolean showResponse) {
        final List<DiagnosticPage> listAllHealthtest = getListShowResponse(showResponse).stream()
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
        final List<DiagnosticPage> listAllSector = getListShowResponse(showResponse).stream()
                .filter(service -> service
                        .getSector()
                        .equals(sector))
                          .collect(Collectors.toList());
        return listAllSector;
    }

    @Override
    public List<DiagnosticPage> findAllServicesBySectorDivisionNoOrResponse(String sector, String division, boolean showResponse) {
        final List<DiagnosticPage> listAllDivision = getListShowResponse(showResponse).stream()
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
