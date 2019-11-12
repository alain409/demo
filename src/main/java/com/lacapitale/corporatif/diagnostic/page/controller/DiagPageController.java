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
    @RequestMapping(value = "DiagnosticPages", method=RequestMethod.GET)
    @ResponseBody

    //Méthode pour afficher la liste des tests triés par secteur ou division et healthtest ou les trois
    public List<DiagnosticPage> listOfServices(@RequestParam(value ="sector", required = false) String secService,
                                               @RequestParam(value ="division", required = false) String divService,
                                               @RequestParam(value ="healthtest",required = false) String healthtestService,
                                               @RequestParam(value ="showResponse",required = false) boolean showResService) {
        if((secService != null) && (divService == null) && (healthtestService == null)) {
            return diagnosticPageDao.findAllServicesBySectorNoOrResponse(secService, showResService);
        }else if((secService != null) && (divService != null) && (healthtestService == null)){
            return diagnosticPageDao.findAllServicesBySectorDivisionNoOrResponse(secService, divService, showResService);
        }else if((secService != null) && (divService != null) && (healthtestService != null)) {
            return diagnosticPageDao.findAllByAllServicesNoOrResponse(secService, divService, healthtestService, showResService);
        }
        return diagnosticPageDao.getAllServicesNoOrWithResponseUrlsError(showResService);
    }
}
