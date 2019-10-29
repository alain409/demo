package com.lacapitale.corporatif.diagnostic.page.dao;

import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;
import com.lacapitale.corporatif.diagnostic.page.service.ServiceProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DiagnosticPageImpl implements DiagnosticPageDao {

    private ServiceProperties serviceProperties;
    private List<DiagnosticPage> listServicesProperties = getDataDiagPage();

    @Override
    public List<DiagnosticPage> getDataDiagPage() {
        serviceProperties = new ServiceProperties();
        return serviceProperties.getFileJson("diagnosticpage.json");
    }

    @Override
    public List<DiagnosticPage> findAll() {
        return listServicesProperties;
    }

    @Override
    public List<DiagnosticPage> findAllByAllServices(String sector, String division,String healthtest) {
        final List<DiagnosticPage> listAllHealthtest = listServicesProperties.stream()
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
    public List<DiagnosticPage> finddAllServicesBySector(String sector) {
        final List<DiagnosticPage> listAllSector = listServicesProperties.stream()
                .filter(service -> service
                        .getSector()
                        .equals(sector))
                .collect(Collectors.toList());
        return listAllSector;
    }

    @Override
    public List<DiagnosticPage> findAllServicesBySectorDivision(String sector, String division) {
        final List<DiagnosticPage> listAllDivision = listServicesProperties.stream()
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
