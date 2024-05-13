package com.HRManager.g01.services;

import com.HRManager.g01.entities.LeaveType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveTypeService {
    List<LeaveType> getLeaveTypes();
    void deleteLeaveType(Long id);
}
