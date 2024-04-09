package com.HRManager.g01.controllers;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.LeaveType;
import com.HRManager.g01.enums.LeaveRequestStatus;
import com.HRManager.g01.services.EmployeService;
import com.HRManager.g01.services.LeaveReqService;
import com.HRManager.g01.services.LeaveTypeService;
import com.HRManager.g01.services.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;
@Controller
public class LeaveRequestController {
    @Autowired
    LeaveReqService leaveReqService;
    @Autowired
    LeaveTypeService leaveTypeService;
    @Autowired
    ManagerService managerService;
    @Autowired
    EmployeService empService;
    @RequestMapping("/createLeaveReq")
    public String createLeaveReq(Model model) {
        List<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes();
        System.out.println("***                                                      Comment view LeaveRequestController  =             *************"+leaveTypes.toString());
        //model.addAttribute("leaveRequest", new LeaveRequest());
        model.addAttribute("leaveTypes", leaveTypes);
        return "Leaves/CreateLeaveRequest";
    }
    @RequestMapping("/saveLeaveReq")
    public String saveLeaveReq(@Valid LeaveRequest leaveRequest, BindingResult br, ModelMap modelMap){
        System.out.println("im here");
        if(br.hasErrors()){
            modelMap.addAttribute("errorMessage","Please check the errors below and try again");
            return "Leaves/CreateLeaveRequest.html";
        }
        //manager , employe , status
        leaveRequest.setManager(managerService.getManagerById(1));
        leaveRequest.setPerson(empService.getEmploye(2));
        leaveRequest.setStatus(LeaveRequestStatus.PENDING);
        //save
        LeaveRequest memo = leaveReqService.saveLeave(leaveRequest);
        List<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes();
        modelMap.addAttribute("leaveTypes", leaveTypes);
        return "Leaves/CreateLeaveRequest";
    }

    @RequestMapping("/listLeaves")
    public String listMyLeaves(ModelMap model){
        List<LeaveRequest> listLeaves = leaveReqService.getLeavesByEmp(2);
        model.addAttribute("leaves",listLeaves);
        return "Leaves/ListLeaves";
    }

    @RequestMapping("/listManagedLeaves")
    public String listManagedLeaves(ModelMap model){
        List<LeaveRequest> listLeaves = leaveReqService.getLeavesByMan(1);
        model.addAttribute("leaves",listLeaves);
        return "Leaves/ListManagedLeaves";
    }
    @RequestMapping("/acceptLeave")
    public String acceptLeave(long id, ModelMap model){
        leaveReqService.acceptLeaveRequest(id);
        System.out.println("accepted successfully");
        return listManagedLeaves(model);

    }

    @RequestMapping("/rejectLeave")
    public String rejectLeave(long id, ModelMap model){
        leaveReqService.rejectLeaveRequest(id);
        System.out.println("rejected successfully");
        return listManagedLeaves(model);

    }



}
