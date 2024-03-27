package com.HRManager.g01.services;

import com.HRManager.g01.entities.LeaveType;
import com.HRManager.g01.repositories.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveTypeServiceImp implements LeaveTypeService{
    @Autowired
    LeaveTypeRepository L_TypeService;
    @Override
    public List<LeaveType> getLeaveTypes() {
        return L_TypeService.findAll();
    }
}
