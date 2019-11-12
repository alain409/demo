package com.lacapitale.corporatif.diagnostic.page.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Override
    public List<DiagnosticPage> getAllDiagnosticPageModel() {
        serviceProperties = new ServiceProperties();
        return serviceProperties.getFileJson();
    }

    @Override
    public List<DiagnosticPage> findAllServicesNoResponse() {
        List<DiagnosticPage> listValueResultNoResponse = new ArrayList<>();
        diagnosticPageService = new DiagnosticPageService();
        for (DiagnosticPage dp : getAllDiagnosticPageModel()
        ) {
            DiagnosticPage diagnosticPage = new DiagnosticPage(
                    dp.getName(), dp.getSector(),
                    dp.getDivision(), dp.getType(),
                    dp.getUrl(), dp.getHealthtest(),
                    dp.getValidationType(),
                    diagnosticPageService.getMatchValidationValue(dp),
                    diagnosticPageService.executeHealthToCheckService(dp),
                    dp.getValidationStateHealthCheck(),
                    dp.getResponse());
            listValueResultNoResponse.add(diagnosticPage);
        }
        return listValueResultNoResponse;
    }

    @Override
    public List<DiagnosticPage> findAllServicesWithResponse() {
        List<DiagnosticPage> listValueResultWithResponse = new ArrayList<>();
        diagnosticPageService = new DiagnosticPageService();
        for (DiagnosticPage dp : getAllDiagnosticPageModel()
        ) {
            DiagnosticPage diagnosticPage = new DiagnosticPage(
                    dp.getName(), dp.getSector(),
                    dp.getDivision(), dp.getType(),
                    dp.getUrl(), dp.getHealthtest(),
                    dp.getValidationType(),
                    diagnosticPageService.getMatchValidationValue(dp),
                    diagnosticPageService.executeHealthToCheckService(dp),
                    dp.getValidationStateHealthCheck(),
                    diagnosticPageService.getStatusUrlHttpOrHttpsService(dp.getUrl()));
            listValueResultWithResponse.add(diagnosticPage);
        }
        return listValueResultWithResponse;
    }

    @Override
    public List<DiagnosticPage> getAllServicesWithResponse() {
       return findAllServicesWithResponse();
    }

    @Override
    public List<DiagnosticPage> getAllServicesNoResponse() {
        return findAllServicesNoResponse();
    }

    @Override
    public DiagnosticPage findAllServicesWithResponseWithUrlsError() {
        diagnosticPageService = new DiagnosticPageService();
        DiagnosticPage diagnosticPageWithResponseError = null;


      for (DiagnosticPage dp : findAllServicesWithResponse()
        ) {
            diagnosticPageWithResponseError = new DiagnosticPage(
                    diagnosticPageService.getStatusResultDiagnosticHealthCheck(dp),
                    diagnosticPageService.getListUrlsHealthCheckError(dp));
        }
        return diagnosticPageWithResponseError;
    }

    @Override
    public DiagnosticPage findAllServicesNoResponseWithUrlsError() {
        diagnosticPageService = new DiagnosticPageService();
        DiagnosticPage diagnosticPageNoResponseError = null;
        for (DiagnosticPage dp : findAllServicesNoResponse()
        ) {
            diagnosticPageNoResponseError = new DiagnosticPage(
                    diagnosticPageService.getStatusResultDiagnosticHealthCheck(dp),
                    diagnosticPageService.getListUrlsHealthCheckError(dp));
        }
        return diagnosticPageNoResponseError;
    }

    @Override
    public List<DiagnosticPage> getAllServicesWithResponseUrlsError() {
        List<DiagnosticPage> globalListServicesWithResponseUrlsError = new ArrayList<>();
        globalListServicesWithResponseUrlsError.add(findAllServicesWithResponseWithUrlsError());
        globalListServicesWithResponseUrlsError.addAll(getAllServicesWithResponse());
        return globalListServicesWithResponseUrlsError;
    }

    @Override
    public List<DiagnosticPage> getAllServicesNoResponseUrlsError() {
        List<DiagnosticPage> globalListServicesNoResponseUrlsError = new ArrayList<>();
        globalListServicesNoResponseUrlsError.add(findAllServicesNoResponseWithUrlsError());
        globalListServicesNoResponseUrlsError.addAll(getAllServicesNoResponse());
        return globalListServicesNoResponseUrlsError;
    }

    @Override
    public List<DiagnosticPage> getListShowResponse(boolean showResponse) {
        if (showResponse)
            return getAllServicesWithResponse();
        return getAllServicesNoResponse();
    }

    @Override
    public DiagnosticPage getDiagnosticPageShowResponseUrlsError(boolean showResponse) {
        if (showResponse)
            return findAllServicesWithResponseWithUrlsError();
        return findAllServicesNoResponseWithUrlsError();
    }

    @Override
    public List<DiagnosticPage> getAllServicesNoOrWithResponseUrlsError(boolean showResponse){
        if(showResponse)
            return getAllServicesWithResponseUrlsError();
        return getAllServicesNoResponseUrlsError();
    }

    @Override
    public List<DiagnosticPage> findAllByAllServicesNoOrResponse(String sector, String division, String healthtest, boolean showResponse) {
        List<DiagnosticPage> listAllServicesNoOrResponse = new ArrayList<>();
        listAllServicesNoOrResponse.add(getDiagnosticPageShowResponseUrlsError(showResponse));
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
        listAllServicesNoOrResponse.addAll(listAllHealthtest);
        return listAllServicesNoOrResponse;
    }

    @Override
    public List<DiagnosticPage> findAllServicesBySectorNoOrResponse(String sector, boolean showResponse) {
        List<DiagnosticPage> listAllServicesBySectorNoOrResponse = new ArrayList<>();
        listAllServicesBySectorNoOrResponse.add(getDiagnosticPageShowResponseUrlsError(showResponse));
        final List<DiagnosticPage> listAllSector = getListShowResponse(showResponse).stream()
                .filter(service -> service
                        .getSector()
                        .equals(sector))
                .collect(Collectors.toList());
        listAllServicesBySectorNoOrResponse.addAll(listAllSector);
        return listAllServicesBySectorNoOrResponse;
    }

    @Override
    public List<DiagnosticPage> findAllServicesBySectorDivisionNoOrResponse(String sector, String division, boolean showResponse) {
        List<DiagnosticPage> listAllServicesBySectorDivisionNoOrResponse = new ArrayList<>();
        listAllServicesBySectorDivisionNoOrResponse.add(getDiagnosticPageShowResponseUrlsError(showResponse));
        final List<DiagnosticPage> listAllSectorDivision = getListShowResponse(showResponse).stream()
                .filter(service -> service
                        .getSector()
                        .equals(sector) &&
                        service
                                .getDivision()
                                .equals(division))
                .collect(Collectors.toList());
        listAllServicesBySectorDivisionNoOrResponse.addAll(listAllSectorDivision);
        return listAllServicesBySectorDivisionNoOrResponse;
    }


    public static void main(String[] args) {
        DiagnosticPageImpl dp2 = new DiagnosticPageImpl();
        System.out.println(dp2.getAllServicesWithResponse());
    }
}
