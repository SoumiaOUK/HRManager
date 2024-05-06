package com.HRManager.g01.controllers;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.LeaveType;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.enums.LeaveRequestStatus;
import com.HRManager.g01.services.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
@Controller
public class LeaveRequestController {
    @Autowired
    LeaveReqServiceImp leaveReqService;
    @Autowired
    LeaveTypeService leaveTypeService;
    @Autowired
    ManagerService managerService;
    @Autowired
    EmployeServiceImp empService;
    @Autowired
    LeaveProofServiceImp leaveProofServiceImp;
    @RequestMapping("/createLeaveReq")
    public String createLeaveReq(Model model) {
        List<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes();
        List<Manager> managers = managerService.getAllManagers();
        System.out.println("***                                                      Comment view LeaveRequestController  =             *************"+leaveTypes.toString());
        //model.addAttribute("leaveRequest", new LeaveRequest());
        model.addAttribute("leaveTypes", leaveTypes);
        model.addAttribute("managers",managers);
        return "Leaves/CreateLeaveRequest";
    }
    @RequestMapping("/saveLeaveReq")
    public String saveLeaveReq(@RequestParam("file") MultipartFile file,@Valid LeaveRequest leaveRequest, BindingResult br, ModelMap modelMap){
        System.out.println("im here");
        if(br.hasErrors()){
            modelMap.addAttribute("errorMessage","Please check the errors below and try again");
            return "Leaves/CreateLeaveRequest.html";
        }
        //manager , employe , status
        //leaveRequest.setManager(managerService.getManagerById(1));
        //leaveRequest.setPerson(empService.getEmploye(2));
        //save
        LeaveRequest memo = leaveReqService.saveLeave(leaveRequest);
        if(memo!=null){
            leaveProofServiceImp.saveLeaveProof(file,memo.getIdLeave());
        }
        List<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes();
        modelMap.addAttribute("leaveTypes", leaveTypes);
        return "Leaves/CreateLeaveRequest";
    }

    @RequestMapping("/listLeaves")
    public String listMyLeaves(ModelMap model){
        List<LeaveRequest> listLeaves = leaveReqService.getLeavesByEmp();
        model.addAttribute("leaves",listLeaves);
        return "Leaves/ListLeaves";
    }

    @RequestMapping("/listManagedLeaves")
    public String listManagedLeaves(ModelMap model){
        List<LeaveRequest> listLeaves = leaveReqService.getLeavesByMan();
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

    @RequestMapping("/addProof")
    public String addProof(Long id){
        try{
        leaveReqService.downloadProof(id);
        }catch(Exception e){
            System.out.println("download faled : "+e.getMessage());
        }
        //redirect to listManagedLeaves
        return null;
    }



    @GetMapping("/downloadProof")
    public ResponseEntity<Resource> downloadProof(long id) {
        try{
            return leaveReqService.downloadProof(id);
        }catch (Exception e){
            System.out.println("error downloading the file : "+e.getMessage());
        }
        return null;
    }
}
