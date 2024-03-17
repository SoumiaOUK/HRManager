package com.HRManager.g01.controllers;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/EmployeList")
    public String employeList(ModelMap modelMap){
        List<Employe> listEmp = empService.getAllEmployees();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empJsp",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "EmployeList";
    }

    @RequestMapping("/deleteEmploye")
    public String employedelete(@RequestParam("id") Long id,ModelMap modelMap){
        empService.deleteEmployeById(id);
        List<Employe> listEmp = empService.getAllEmployees();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empJsp",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "EmployeList";
    }


}