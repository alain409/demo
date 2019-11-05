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
    @RequestMapping(value = "DiagnosticPages")
    @ResponseBody
    public List<DiagnosticPage> listOfService(@RequestParam(value ="sector", required = false) String secService,
                                              @RequestParam(value ="division", required = false) String divService,
                                              @RequestParam(value ="healthtest",required = false) String hlttestService,
                                              @RequestParam(value ="showResponse",required = false) boolean showResService) {
        // List<DiagnosticPage> diagnosticPageListResponse = null;
        if (secService != "") {
            return diagnosticPageDao.findAllServicesBySectorNoOrResponse(secService, showResService);
        } else if ((divService != "") &&
                (secService != "")) {
            return diagnosticPageDao.findAllByAllServicesNoOrResponse(secService, divService, hlttestService, showResService);
        } else if ((hlttestService != "")
                && (secService != "") &&
                (divService != "")) {
            return diagnosticPageDao.findAllServicesBySectorDivisionNoOrResponse(secService, divService, showResService);
        }
        return diagnosticPageDao.getDataDiagPageNoResponse();
    }
}
