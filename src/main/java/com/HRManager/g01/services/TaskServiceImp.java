package com.HRManager.g01.services;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.entities.Tasks;
import com.HRManager.g01.repositories.TaskRepository;
import com.HRManager.g01.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TaskServiceImp implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Tasks> getAllTasks() {
        List<Tasks> allTasks = taskRepository.findAll();
        return allTasks;
    }
    @Override
    public List<Tasks> getTasksByExecutor(Long id) {
        List<Tasks> allTask = taskRepository.getTasksByExecutor(id);
        return allTask;
    }
    @Override
    public List<Tasks> getTasksByManager() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("LeaveReqServiceImpl \n name = "+currentPrincipalName+"\n personne = "+p.toString()+"\n MANAGER "+ p.getMyManager());
        List<Tasks> allTasks = taskRepository.getTasksByManager(p.getIdPerson());
        return allTasks;
    }


    @Override
    public Tasks save(Tasks task){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
        Person p =  userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("LeaveReqServiceImpl \n name = "+currentPrincipalName+"\n personne = "+p.toString()+"\n MANAGER "+ p.getMyManager());
        task.setManager((Manager) p);
        return taskRepository.save(task);
    }
    @Override
    public List<Tasks> getMyTasks(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
        Person p =  userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("LeaveReqServiceImpl \n name = "+currentPrincipalName+"\n personne = "+p.toString()+"\n MANAGER "+ p.getMyManager());

        List<Tasks> tasks = getTasksByExecutor(p.getIdPerson());
        return tasks;
    }
}