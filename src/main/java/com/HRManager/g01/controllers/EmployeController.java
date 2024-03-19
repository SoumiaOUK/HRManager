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

    @RequestMapping("/createEmploye")
    public String createEmploye(){
        return "CreateEmploye";
    }

    //comment le controller communique avec les vues
    @RequestMapping("/saveEmploye")
    public String saveEmploye(@ModelAttribute("employe") Employe employe){
        //faire passer info from DB to th
        Employe memo = empService.saveEmploye(employe);
         return "CreateEmploye";
    }

    @RequestMapping("/employeList")
    public String employeList(ModelMap modelMap){
        List<Employe> listEmp = empService.getAllEmployees();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empTh",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "EmployeList";
    }

    @RequestMapping("/deleteEmploye")
    public String deleteEmploye(@RequestParam("id") Long id,ModelMap modelMap){
        empService.deleteEmployeById(id);
        List<Employe> listEmp = empService.getAllEmployees();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empTh",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "EmployeList";
    }

    @RequestMapping("/showEmploye")
    public String showEmploye(@RequestParam("id") Long id ,ModelMap modelMap){
        Employe emp = empService.getEmploye(id);
        modelMap.addAttribute("empTh",emp);
        return "EditEmploye";
    }


    @RequestMapping("/updateEmploye")
    public String updateEmploye( @ModelAttribute("employe") Employe employe){
        System.out.println("**************"+employe);
        empService.saveEmploye(employe);
        return "EditEmploye";
    }


}