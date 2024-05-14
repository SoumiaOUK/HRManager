package com.HRManager.g01.controllers;
import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.Tasks;
import com.HRManager.g01.services.PersonServiceImp;
import com.HRManager.g01.services.TaskServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TasksController {

    @Autowired
    TaskServiceImp taskServiceImp;
    @Autowired
    PersonServiceImp personServiceImp;


    @RequestMapping("/listManagedTasks")
    public String listTasks(ModelMap modelMap){
        List<Tasks> tasks = taskServiceImp.getTasksByManager();
        modelMap.addAttribute("tasks",tasks);
        tasks.forEach(System.out::println);
        return "Tasks/AllManagedTasks";
    }


    @RequestMapping("/listEmployeTasks")
    public String listemployeTasks(@RequestParam("id")Long id ,ModelMap modelMap){
        List<Tasks> tasks = taskServiceImp.getTasksByExecutor(id);
        modelMap.addAttribute("tasks",tasks);
        return "Tasks/TasksByExecutor";
    }

    @RequestMapping("/listMyTasks")
    public String listMyTasks(ModelMap modelMap){
        List<Tasks> tasks = taskServiceImp.getMyTasks();
        modelMap.addAttribute("tasks",tasks);
        return "Tasks/TasksByExecutor";
    }
    @RequestMapping("/createTask")
    public String createTask(ModelMap modelMap){
        List<Employe> employees =personServiceImp.getlistEmpByManager();
        System.out.println("\n TasksController  : createTask");
        employees.forEach(System.out::println);
        modelMap.addAttribute("emps",employees);
        return "Tasks/CreateTask";
    }
    @RequestMapping("/saveTask")
    public String saveTask(@Valid Tasks task,BindingResult br, ModelMap modelMap){

        System.out.println("\n TasksController  saveTask  :  \n  ");
        Tasks newtask = taskServiceImp.save(task);

        System.out.println(newtask.toString());
        if(br.hasErrors()){
            modelMap.addAttribute("errorMessage","Please check the errors below and try again");
            return "redirect:createTask ";
        }

        try{

        //Tasks newtask = taskServiceImp.save(task);
            System.out.println("\n TasksController  saveTask  :  \n  ");

            System.out.println(newtask.toString());
        }catch (Exception e){
            System.out.println("\n TasksController  saveTask  :   "+e.getMessage());
        }
        return "redirect:/listManagedTasks";
    }
}
