package com.HRManager.g01.services;

import com.HRManager.g01.entities.Tasks;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    List<Tasks> getAllTasks();
    List<Tasks> getTasksByExecutor(Long id);
    List<Tasks> getTasksByManager();
    Tasks save(Tasks task);
    List<Tasks> getMyTasks();

}
