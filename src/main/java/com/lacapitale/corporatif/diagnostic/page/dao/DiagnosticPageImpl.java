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
    //La méthode principale qui appèlle le fichier Json
    public List<DiagnosticPage> getAllDiagnosticPageModel() {
        serviceProperties = new ServiceProperties();
        return serviceProperties.getFileJson();
    }

    @Override
    //Récupère tous les tests sans le détail de la réponse
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
    //Récupère tous les tests avec le détail de la réponse
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
    //Appel de la méthode qui récupère les tests avec le détail de la réponse
    public List<DiagnosticPage> getAllServicesWithResponse() {
        return findAllServicesWithResponse();
    }

    @Override
    //Appel de la méthode qui récupère les tests sans le détail de la réponse
    public List<DiagnosticPage> getAllServicesNoResponse() {
        return findAllServicesNoResponse();
    }


    @Override
    //Récupére l'objet DiagnosticPage qui contient la liste des urls des tests(avec le détail de réponse) en erreur
    public DiagnosticPage findAllServicesWithResponseWithUrlsError() {
        diagnosticPageService = new DiagnosticPageService();
            return new DiagnosticPage(diagnosticPageService.getStatusResultDiagnosticHealthCheck(),
                                       diagnosticPageService.getListUrlsHealthCheckWithResponseError());
    }

    @Override
    //Récupére l'objet DiagnosticPage qui contient la liste des urls des tests(sans le détail de réponse) en erreur
    public DiagnosticPage findAllServicesNoResponseWithUrlsError() {
        diagnosticPageService = new DiagnosticPageService();
            return new DiagnosticPage(diagnosticPageService.getStatusResultDiagnosticHealthCheck(),
                           diagnosticPageService.getListUrlsHealthCheckNoResponseError());
    }

    @Override
    //Appel global des tests(avec détail de la réponse) et la liste des urls des tests en erreur
    public List<DiagnosticPage> getAllServicesWithResponseUrlsError() {
        List<DiagnosticPage> globalListServicesWithResponseUrlsError = new ArrayList<>();
        globalListServicesWithResponseUrlsError.add(findAllServicesWithResponseWithUrlsError());
        globalListServicesWithResponseUrlsError.addAll(getAllServicesWithResponse());
        return globalListServicesWithResponseUrlsError;
    }

    @Override
    //Appel global des tests(sans détail de la réponse) et la liste des urls des tests en erreur
    public List<DiagnosticPage> getAllServicesNoResponseUrlsError() {
        List<DiagnosticPage> globalListServicesNoResponseUrlsError = new ArrayList<>();
        globalListServicesNoResponseUrlsError.add(findAllServicesNoResponseWithUrlsError());
        globalListServicesNoResponseUrlsError.addAll(getAllServicesNoResponse());
        return globalListServicesNoResponseUrlsError;
    }

    @Override
    //Récupère la liste des tests dépendement du paramètre de sortie showResponse(vraie ou faux)
    public List<DiagnosticPage> getListShowResponse(boolean showResponse) {
        if (showResponse)
            return getAllServicesWithResponse();
        return getAllServicesNoResponse();
    }

    @Override
    //Récupère l'objet DiagnosticPage dépendement du paramètre de sortie showResponse(vraie ou faux)
    public DiagnosticPage getDiagnosticPageShowResponseUrlsError(boolean showResponse) {
        if (showResponse)
            return findAllServicesWithResponseWithUrlsError();
        return findAllServicesNoResponseWithUrlsError();
    }

    @Override
    //Récupère la liste glogal des tests plus la liste des urls des tests en erreur, dépendement du paramètre de sortie showResponse(vraie ou faux)
    public List<DiagnosticPage> getAllServicesNoOrWithResponseUrlsError(boolean showResponse){
        if(showResponse)
            return getAllServicesWithResponseUrlsError();
        return getAllServicesNoResponseUrlsError();
    }

    @Override
    //Récupère la liste qui filtre les tests par sector, division et healthtest en sortie
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
    //Récupère la liste qui filtre les tests par sector en sortie
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
    //Récupère la liste qui filtre les tests par sector et division en sortie
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
}
