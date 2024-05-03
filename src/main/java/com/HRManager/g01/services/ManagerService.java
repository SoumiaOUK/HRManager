package com.HRManager.g01.services;

import com.HRManager.g01.entities.Manager;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ManagerService {
    public Manager getManagerById(long id);
    public List<Manager> getAllManagers();
}
