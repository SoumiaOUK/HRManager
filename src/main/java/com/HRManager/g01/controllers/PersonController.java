package com.HRManager.g01.controllers;

import com.HRManager.g01.entities.Person;
import com.HRManager.g01.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//intermediare avec service
public class PersonController {
    @Autowired
    PersonService empService;

    @RequestMapping("/createPerson")
    public String createPerson(){
        return "CreatePerson";
    }

    //comment le controller communique avec les vues
    @RequestMapping("/savePerson")
    public String savePerson(@Valid Person Person, BindingResult bR,ModelMap modelMap){
        if (bR.hasErrors()) {
            // If there are validation errors, add an error message to the model
            modelMap.addAttribute("errorMessage", "Please fix the validation errors and submit again.");
            return "CreatePerson"; // Return to the form with the error message
        }
        //faire passer info from DB to th
        Person memo = empService.savePerson(Person);
        return "CreatePerson";
    }

    @RequestMapping("/PersonList")
    public String PersonList(ModelMap modelMap){
        List<Person> listEmp = empService.getAllPersones();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empTh",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "PersonList";
    }

    @RequestMapping("/deletePerson")
    public String deletePerson(@RequestParam("id") Long id,ModelMap modelMap){
        empService.deletePersonById(id);
        List<Person> listEmp = empService.getAllPersones();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empTh",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "PersonList";
    }

    @RequestMapping("/showPerson")
    public String showPerson(@RequestParam("id") Long id ,ModelMap modelMap){
        Person emp = empService.getPerson(id);
        modelMap.addAttribute("empTh",emp);
        return "EditPerson";
    }

/*
    @RequestMapping("/updatePerson")
    public String updatePerson( @ModelAttribute("Person") Person Person,@RequestParam(idPerson) Long idPerson){
        System.out.println("**************"+Person);
        empService.savePerson(Person);
        return "EditPerson";
    }

 */


}