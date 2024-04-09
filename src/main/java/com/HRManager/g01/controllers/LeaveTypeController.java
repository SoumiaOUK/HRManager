package com.HRManager.g01.controllers;

import com.HRManager.g01.entities.LeaveType;
import com.HRManager.g01.services.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LeaveTypeController {
@Autowired
    LeaveTypeService leaveTypeService;
@RequestMapping("/listLeaveTypes")
    public String listLeaveTypes(ModelMap modelMap){
    List<LeaveType> leaveTypes=  leaveTypeService.getLeaveTypes();
    System.out.println("====="+leaveTypes.toString());
    modelMap.addAttribute("types",leaveTypes);
    return "LeaveTypes/ListLeaveTypes";
}
}
