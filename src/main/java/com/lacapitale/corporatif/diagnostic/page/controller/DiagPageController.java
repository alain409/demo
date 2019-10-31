package com.lacapitale.corporatif.diagnostic.page.controller;

import com.lacapitale.corporatif.diagnostic.page.dao.DiagnosticPageDao;
import com.lacapitale.corporatif.diagnostic.page.model.DiagnosticPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiagPageController {

    @Autowired
    private DiagnosticPageDao diagnosticPageDao;


    //Méthode qui retourne tous les services soit par secteur soit par secteur et division, ou par secteur division et healthtest
    @GetMapping(value = "DiagnosticPages")
    @ResponseBody
    public List<DiagnosticPage> listOfService(@RequestParam String sector,
                                              @RequestParam String division,
                                              @RequestParam String healthtest,
                                              @RequestParam boolean showResponse) {
       List<DiagnosticPage> diagnosticPageListResponse = null;

        if ((division == "") &&
                (healthtest == "") &&
                (sector != "")) {
            diagnosticPageListResponse = diagnosticPageDao.findAllServicesBySectorNoOrResponse(sector,showResponse);
        } else if ((division != "") &&
                (sector != "") &&
                (healthtest != "")) {
            diagnosticPageListResponse = diagnosticPageDao.findAllByAllServicesNoOrResponse(sector, division,healthtest,showResponse);

        } else if ((healthtest == "")
                && (sector != "") &&
                (division != "")) {
            diagnosticPageListResponse = diagnosticPageDao.findAllServicesBySectorDivisionNoOrResponse(sector, division, showResponse);
            } else {
            diagnosticPageListResponse = diagnosticPageDao.getDataDiagPageNoResponse();
        }
        return diagnosticPageListResponse;
    }
}
