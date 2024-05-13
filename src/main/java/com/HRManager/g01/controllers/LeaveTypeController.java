package com.HRManager.g01.controllers;
import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveType;
import com.HRManager.g01.services.LeaveTypeService;
import com.HRManager.g01.services.LeaveTypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class LeaveTypeController {
@Autowired
LeaveTypeServiceImp leaveTypeService;
@RequestMapping("/listLeaveTypes")
    public String listLeaveTypes(ModelMap modelMap){
    List<LeaveType> leaveTypes=  leaveTypeService.getLeaveTypes();
    System.out.println("====="+leaveTypes.toString());
    modelMap.addAttribute("types",leaveTypes);
    return "LeaveTypes/ListLeaveTypes";
}
@RequestMapping("/createLeaveType")
    public String createLeaveType(){
    return "LeaveTypes/addLeaveType";
}
@RequestMapping("/saveLeaveType")
    public String saveLeaveType(@ModelAttribute("leaveType")LeaveType leaveType, BindingResult bR,ModelMap modelMap){
    if (bR.hasErrors()) {
        // If there are validation errors, add an error message to the model
        modelMap.addAttribute("errorMessage", "Please fix the validation errors and submit again.");
        return "createLeaveType"; // Return to the form with the error message
    }
    //faire passer info from DB to th
    System.out.println(leaveType);
    LeaveType newLeaveType = leaveTypeService.saveLeaveType(leaveType);

    return "redirect:/listLeaveTypes";
}
@RequestMapping("/showLeaveType")
    public String showLeaveType(@RequestParam("id") Long id , ModelMap modelMap){
    System.out.println("\n id :   "+id);
    LeaveType leaveType = leaveTypeService.findLeaveTypeById(id);
    modelMap.addAttribute("leaveType",leaveType);
    return "LeaveTypes/UpdateLeaveType";}

    @RequestMapping("/updateLeaveType")
    public String updateLeaveType( @ModelAttribute("leaveType") LeaveType leaveType){
        leaveTypeService.saveLeaveType(leaveType);
        return "redirect:/listLeaveTypes";
    }

    @RequestMapping("/deleteLeaveType")
    public String deleteLeaveType(@RequestParam("id") Long id,ModelMap modelMap){
    leaveTypeService.deleteLeaveType(id);
    return "redirect:/listLeaveTypes";
    }
}