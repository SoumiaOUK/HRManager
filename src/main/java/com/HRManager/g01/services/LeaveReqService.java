package com.HRManager.g01.services;

import com.HRManager.g01.entities.LeaveRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

@Service
public interface LeaveReqService {
    //create Leave
    public LeaveRequest saveLeave(LeaveRequest leaveRequest);
    //
    public LeaveRequest readLeaveReq(Long id);
    //read Leave
    public List<LeaveRequest> getLeavesByEmp();
    public List<LeaveRequest> getLeavesByMan();
    //update Leave
    public LeaveRequest updateLeaveRequest(LeaveRequest leaveReq);
    public void deleteLeaveRequest(Long id);
    public void acceptLeaveRequest(long id);
    public void rejectLeaveRequest(long id);

    int countLeaves();

    ResponseEntity<Resource> downloadProof(Long idProof) throws MalformedURLException, FileNotFoundException;

}
