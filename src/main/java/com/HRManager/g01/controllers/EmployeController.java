package com.HRManager.g01.controllers;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//intermediare avec service
public class EmployeController {
    @Autowired
    EmployeService empService;

    @RequestMapping("/CreateEmploye")
    public String createEmloye(){
        return "CreateEmploye";
    }

    //comment le controller communique avec les vues
    @RequestMapping("saveEmploye")
    public String saveEmploye(
            //faire passer le produit du jsp au controller
            @ModelAttribute("employe") Employe employe,
            ModelMap modelMap
    ){
        //faire passer info from DB to jsp
        Employe memo = empService.saveEmploye(employe);
        String messageFromController = "L'employe  : "+memo.getNom() +"a été ajouter avec succes son id est "+memo.getId();
        //envoyer message vers jsp
        modelMap.addAttribute("messageJsp",messageFromController);
        return "UserCreated";
    }


}