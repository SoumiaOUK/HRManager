package com.HRManager.g01.controllers;
import com.HRManager.g01.entities.Bonus;
import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.Tasks;
import com.HRManager.g01.services.BonusServiceImp;
import com.HRManager.g01.services.PersonServiceImp;
import com.HRManager.g01.services.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@Controller
public class BonusController {
    @Autowired
    BonusServiceImp bonusServiceImp;
    @Autowired
    PersonServiceImp personServiceImp;
    @Autowired
    TaskServiceImp taskServiceImp;
    @RequestMapping("/listManagedBonus")
    public String listManagedBonus(ModelMap modelMap){
        List<Bonus> bonuses = bonusServiceImp.getAllBonusesByMan();
        modelMap.addAttribute("bonuses",bonuses);
        return "Bonus/ListManagedBonus";
    }
    @RequestMapping("/listBonusByEmp")
    public String listBonusByEmp(@RequestParam("id") Long id,ModelMap modelMap){
        List<Bonus> bonuses = bonusServiceImp.getBonusByEmp(id);
        modelMap.addAttribute("bonuses",bonuses);
        return "Bonus/ListBonusByEmp";
    }
    @RequestMapping("listMyBonus")
    public String listMyBonus(ModelMap modelMap){
        List<Bonus> bonuses = bonusServiceImp.getMyBonuses();
        modelMap.addAttribute("bonuses",bonuses);
        return "Bonus/ListMyBonus";
    }

    @RequestMapping("createBonus")
    public String createBonus(ModelMap modelMap){
        List<Employe> employees =personServiceImp.getlistEmpByManager();
        modelMap.addAttribute("emps",employees);

        List<Tasks> tasks = taskServiceImp.getTasksByManager();
        modelMap.addAttribute("tasks",tasks);

        return "Bonus/AssignBonus";
    }

    @RequestMapping("saveBonus")
    public String saveBonus(@ModelAttribute("bonus") Bonus bonus){
        bonusServiceImp.save(bonus);
        return "redirect:/listManagedBonus";
    }

}
