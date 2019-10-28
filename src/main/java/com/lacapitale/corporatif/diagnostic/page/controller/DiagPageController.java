package com.lacapitale.corporatif.diagnostic.page.controller;

import com.lacapitale.corporatif.diagnostic.page.dao.DiagnosticPageDao;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DiagPageController {

    @Autowired
    private DiagnosticPageDao diagnosticPageDao;

    //Méthode qui retourne tous les services
  /*  @GetMapping(value = "/DiagnosticPages")
    public List<DiagnosticPage> listOfService() {
        return diagnosticPageDao.findAll();
    }*/

    //Méthode pour récupérer les tests CGEN et CAAP
    @GetMapping(value = "DiagnosticPages")
    @ResponseBody
    public Optional<DiagnosticPage> listOfService(@RequestParam(value = "sector") String sector) {
        Optional<DiagnosticPage> diagnosticPageList = null;
        switch (sector){
            case "CGEN":
                diagnosticPageList = diagnosticPageDao.findAllServicesCgen();
                break;
            case "CAAP":
                diagnosticPageList = diagnosticPageDao.findAllServicesCaap();
                break;
                default:
                    break;
        }
        return diagnosticPageList;
    }

    //Méthode qui retourne un service par son endPoint
    @GetMapping(value = "DiagnosticPages/{endPoint}")
    public Optional<DiagnosticPage> getOneService(@PathVariable String endPoint) {

        Optional<DiagnosticPage> optionalDiagnosticPage = null;
        switch (endPoint) {
            case "advisorSpace":
                optionalDiagnosticPage = diagnosticPageDao.findByEndPointAdvisorSpace(endPoint);
                break;

            case "capCourtier":
                optionalDiagnosticPage = diagnosticPageDao.findByEndPointCapCourtier(endPoint);
                break;

            case "pubPost":
                optionalDiagnosticPage = diagnosticPageDao.findByEndPointPubPost(endPoint);
                break;

            case "ecEpargne":
                optionalDiagnosticPage = diagnosticPageDao.findByEndPointEcEpargne(endPoint);
                break;
            default:
                break;
        }
        return optionalDiagnosticPage;
    }
}
