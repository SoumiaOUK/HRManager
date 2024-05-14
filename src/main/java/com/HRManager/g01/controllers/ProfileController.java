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

       //fetch tasks :
       List<Tasks> tasks = taskServiceImp.getMyTasks();
       System.out.println("\n Profile Controller =     tasks");
       tasks.forEach(System.out::println);
       modelMap.addAttribute("tasks",tasks);
       //fetch leaves:
       System.out.println("\n Profile Controller =     leaves");
       List<LeaveRequest> leaves = leaveReqService.getMyLeaves();
       leaves.forEach(System.out::println);
       modelMap.addAttribute("leaves",leaves);

       //fetch bonuses:
       System.out.println("\n Profile Controller =     bonuses");
       List<Bonus> bonuses = bonusServiceImp.getMyBonuses();
       bonuses.forEach(System.out::println);
       modelMap.addAttribute("bonuses",bonuses);


       return "Profile/MyProfile";
   }
    @RequestMapping("/showProfile")
    public String showProfile(@RequestParam("id") Long id , ModelMap modelMap){
        Person profileDetails = personServiceImp.getPerson(id);
        modelMap.addAttribute("profile",profileDetails);

        //fetch tasks :
        List<Tasks> tasks = taskServiceImp.getTasksByExecutor(id);
        System.out.println("\n Profile Controller =     tasks");
        tasks.forEach(System.out::println);
        modelMap.addAttribute("tasks",tasks);
        //fetch leaves:
        System.out.println("\n Profile Controller =     leaves");
        List<LeaveRequest> leaves = leaveReqService.getLeavesByEmp(id);
        leaves.forEach(System.out::println);
        modelMap.addAttribute("leaves",leaves);

        //fetch bonuses:
        System.out.println("\n Profile Controller =     bonuses");
        List<Bonus> bonuses = bonusServiceImp.getMyBonuses();
        bonuses.forEach(System.out::println);
        modelMap.addAttribute("bonuses",bonuses);


        return "Profile/MyProfile";
    }

}
