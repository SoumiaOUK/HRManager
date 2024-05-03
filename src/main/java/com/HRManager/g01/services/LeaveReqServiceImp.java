package com.HRManager.g01.services;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.enums.LeaveRequestStatus;
import com.HRManager.g01.repositories.LeaveRequestRepository;
import com.HRManager.g01.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
public class LeaveReqServiceImp implements LeaveReqService{
    @Autowired
    LeaveRequestRepository leaveReqRep;
    @Autowired
    UserRepository userRepository;
    @Override
    public LeaveRequest saveLeave(LeaveRequest leaveRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("LeaveReqServiceImpl \n name = "+currentPrincipalName+"\n personne = "+p.toString());
        leaveRequest.setPerson(p);
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
