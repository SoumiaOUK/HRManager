package com.HRManager.g01.services;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.enums.LeaveRequestStatus;
import com.HRManager.g01.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
public class LeaveReqServiceImp implements LeaveReqService{
    @Autowired
    LeaveRequestRepository leaveReqRep;
    @Override
    public LeaveRequest saveLeave(LeaveRequest leaveRequest) {
        return leaveReqRep.save(leaveRequest);
    }
    @Override
    public LeaveRequest readLeaveReq(Long id) {
        return leaveReqRep.findById(id).get();
    }
    @Override
    public LeaveRequest updateLeaveRequest(LeaveRequest leaveReq) {
        return leaveReqRep.save(leaveReq);
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        leaveReqRep.deleteById(id);
    }

    //to search
    @Override
    public List<LeaveRequest> getLeavesByEmp(long id) {return leaveReqRep.listLeavesByEmp(id);}
    @Override
    public List<LeaveRequest> getLeavesByMan(long idmanager) {
        return leaveReqRep.listLeavesByMan(idmanager);
    }
    @Override
    public void acceptLeaveRequest(long id){
        LeaveRequest leave  = leaveReqRep.findById(id).get();
        leave.setStatus(LeaveRequestStatus.APPROVED);
        leaveReqRep.save(leave);

    }
    @Override
    public void rejectLeaveRequest(long id){
        LeaveRequest leave  = leaveReqRep.findById(id).get();
        leave.setStatus(LeaveRequestStatus.APPROVED);
        leaveReqRep.save(leave);
    }
}
