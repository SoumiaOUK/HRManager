package com.HRManager.g01.controllers;

import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.services.RoleServiceImp;
import com.HRManager.g01.services.ManagerServiceImp;
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
    PersonService personService;
    @Autowired
    ManagerServiceImp managerServiceImp;
    @Autowired
    RoleServiceImp roleServiceImp;

    @RequestMapping("/createPerson")
    public String createPerson(ModelMap modelMap){
        List<Manager> managers = managerServiceImp.getAllManagers();
        List<Role> roles = roleServiceImp.getRoles();
        modelMap.addAttribute("managers",managers);
        modelMap.addAttribute("roles",roles);
        roles.forEach(System.out::println);
        return "Person/CreatePerson";
    }

    //comment le controller communique avec les vues
    @RequestMapping("/savePerson")
    public String savePerson(@Valid Person Person,BindingResult bR,ModelMap modelMap){
        if (bR.hasErrors()) {

            // If there are validation errors, add an error message to the model
            modelMap.addAttribute("errorMessage", "Please fix the validation errors and submit again.");
            return "CreatePerson"; // Return to the form with the error message
        }
        //System.out.println(Person.getMyManager().getIdPerson());
        System.out.println(Person.toString());
        String role=Person.getRole();
        System.out.println("\n \n \n  role"+Person.getRole());
        //faire passer info from DB to th
       Person memo = personService.savePerson(Person,role);
        return "CreatePerson";

    }

    @RequestMapping("/PersonList")
    public String PersonList(ModelMap modelMap){
        System.out.println("welcome to personlist");
        List<Person> listEmp = personService.getAllPersones();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("personTh",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "PersonList";
    }

    @RequestMapping("/deletePerson")
    public String deletePerson(@RequestParam("id") Long id,ModelMap modelMap){
        personService.deletePersonById(id);
        List<Person> listEmp = personService.getAllPersones();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("personTh",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "PersonList";
    }

    @RequestMapping("/showPerson")
    public String showPerson(@RequestParam("id") Long id ,ModelMap modelMap){
        Person emp = personService.getPerson(id);
        modelMap.addAttribute("personTh",emp);
        return "EditPerson";
    }

/*
    @RequestMapping("/updatePerson")
    public String updatePerson( @ModelAttribute("Person") Person Person,@RequestParam(idPerson) Long idPerson){
        System.out.println("**************"+Person);
        personService.savePerson(Person);
        return "EditPerson";
    }

 */


}