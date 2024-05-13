package com.HRManager.g01.controllers;
import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.services.RoleServiceImp;
import com.HRManager.g01.services.EmployeService;
import com.HRManager.g01.services.EmployeServiceImp;
import com.HRManager.g01.services.ManagerServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@Controller
//intermediare avec service
public class EmployeController {
    @Autowired
    RoleServiceImp roleServiceImp;
    @Autowired
    EmployeServiceImp empService;
    @Autowired
    ManagerServiceImp managerServiceImp;
    @RequestMapping("/createEmploye")
    public String createEmploye(ModelMap modelMap){
        //List<Manager> managers = managerServiceImp.getAllManagers();
        //modelMap.addAttribute("managers",managers);
        //managers.forEach(System.out::println);
        List<Role> roles= roleServiceImp.getRoles();
        roles.forEach(System.out::println);
        modelMap.addAttribute("roles",roles);
        return "CreateEmploye";
    }
    //comment le controller communique avec les vues
    @RequestMapping("/saveEmploye")
    public String saveEmploye(@Valid Employe employe, BindingResult bR,ModelMap modelMap){
        if (bR.hasErrors()) {
            // If there are validation errors, add an error message to the model
            modelMap.addAttribute("errorMessage", "Please fix the validation errors and submit again.");
            return "CreateEmploye"; // Return to the form with the error message
        }
        //faire passer info from DB to th
        Employe memo = empService.saveEmploye(employe);
        return "redirect:/createEmploye"; // Redirect to the createEmploye page after successful save
    }
    @RequestMapping("/employeList")
    public String employeList(ModelMap modelMap){
        List<Employe> listEmp = empService.getEmployeesByManager();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empTh",listEmp);
        System.out.println("print employees to console\n ");
        listEmp.forEach(System.out::println);
        //retourn nom d'une view qu'on va chercher dans Views
        return "EmployeList";
    }
    @RequestMapping("/deleteEmploye")
    public String deleteEmploye(@RequestParam("id") Long id,ModelMap modelMap){
        empService.deleteEmployeById(id);
        List<Employe> listEmp = empService.getEmployeesByManager();
        //on envoie la list du controlleur vers jsp using model map
        modelMap.addAttribute("empTh",listEmp);
        //retourn nom d'une view qu'on va chercher dans Views
        return "EmployeList";
    }
    @RequestMapping("/showEmploye")
    public String showEmploye(@RequestParam("id") Long id ,ModelMap modelMap){
        System.out.println("\n id :   "+id);
        Employe emp = empService.getEmploye(id);
        System.out.println("employe   :   "+emp.toString());
        modelMap.addAttribute("empTh",emp);
        return "EditEmploye";
    }
    @RequestMapping("/updateEmploye")
    public String updateEmploye( @ModelAttribute("employe") Employe employe,@RequestParam("idPerson") Long idPerson,ModelMap modelMap){
        System.out.println("\n id employe to update "+idPerson);
        System.out.println("**************"+employe.getDepartement());
        empService.updateEmploye(employe);
        Employe emp=empService.getEmploye(idPerson);
        modelMap.addAttribute("empTh",emp);
        return "redirect:/employeList";
    }
}