package com.HRManager.g01.services;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveReqService {
    //create Leave
    public LeaveRequest saveLeave(LeaveRequest leaveRequest);
    //
    public LeaveRequest readLeaveReq(Long id);
    //read Leave
    public List<LeaveRequest> getLeavesByEmp(long id);
    public List<LeaveRequest> getLeavesByMan(long id );
    //update Leave
    public LeaveRequest updateLeaveRequest(LeaveRequest leaveReq);
    public void deleteLeaveRequest(Long id);
    public void acceptLeaveRequest(long id);
    public void rejectLeaveRequest(long id);

}
