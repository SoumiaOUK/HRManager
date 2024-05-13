package com.HRManager.g01.controllers;

import com.HRManager.g01.entities.Bonus;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.entities.Tasks;
import com.HRManager.g01.security.repositories.UserRepository;
import com.HRManager.g01.services.BonusServiceImp;
import com.HRManager.g01.services.LeaveReqServiceImp;
import com.HRManager.g01.services.PersonServiceImp;
import com.HRManager.g01.services.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    PersonServiceImp personServiceImp;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskServiceImp taskServiceImp;
    @Autowired
    LeaveReqServiceImp leaveReqService;
    @Autowired
    BonusServiceImp bonusServiceImp;

   @RequestMapping("/myProfile")
    public String myProfile(ModelMap modelMap){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentPrincipalName = authentication.getName();
       System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
       Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
       modelMap.addAttribute("profile",p);
       return "Profile/MyProfile";
   }
    @RequestMapping("/showProfile")
    public String showProfile(@RequestParam("id") Long id , ModelMap modelMap){
        Person profileDetails = personServiceImp.getPerson(id);
        modelMap.addAttribute("profile",profileDetails);

        //fetch tasks :
        List<Tasks> tasks = taskServiceImp.getTasksByExecutor(id);
        modelMap.addAttribute("tasks",tasks);
        //fetch leaves:
        List<LeaveRequest> leaves = leaveReqService.getLeavesByEmp(id);
        modelMap.addAttribute("leaves",leaves);

        //fetch bonuses:
        List<Bonus> bonuses = bonusServiceImp.getMyBonuses();
        modelMap.addAttribute("bonuses",bonuses);


        return "Profile/MyProfile";
    }

}
