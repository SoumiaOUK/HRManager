package com.HRManager.g01.services;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.enums.LeaveRequestStatus;
import com.HRManager.g01.repositories.LeaveProofRepo;
import com.HRManager.g01.repositories.LeaveRequestRepository;
import com.HRManager.g01.security.repositories.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class LeaveReqServiceImp implements LeaveReqService{
    @Autowired
    LeaveRequestRepository leaveReqRep;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LeaveProofServiceImp leaveProofServiceImp;
    @Override
    public LeaveRequest saveLeave(LeaveRequest leaveRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("LeaveReqServiceImpl currentPrincipalName"+currentPrincipalName);
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("LeaveReqServiceImpl \n name = "+currentPrincipalName+"\n personne = "+p.toString()+"\n MANAGER "+ p.getMyManager());
        leaveRequest.setPerson(p);
        leaveRequest.setManager(p.getMyManager());

        leaveRequest.setStatus(LeaveRequestStatus.PENDING);

        int duration=0;
        Date startDate = leaveRequest.getStartDate();
        Date endDate = leaveRequest.getEndDate();
        if (startDate != null && endDate != null) { // Ensure duration is not already set to avoid recursion
            long diff = endDate.getTime() - startDate.getTime();
            duration = (int) (diff / (1000 * 60 * 60 * 24)); // Convert milliseconds to days
        }
        int soldeRestant = p.getSoldeConges();
        if (soldeRestant<duration){
            System.out.println("The requested leave duration exceeds your available leave balance. Please adjust your leave request accordingly.\n");
        }else {
            System.out.println("\n actual leave balance  :  "+soldeRestant);
            soldeRestant=soldeRestant-duration;
            p.setSoldeConges(soldeRestant);
            System.out.println("\n new assigned leave balance  :  "+p.getSoldeConges());
            return leaveReqRep.save(leaveRequest);

        }

    return null;

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
    public List<LeaveRequest> getLeavesByEmp() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("\n LEAVEREQSERVICEIMP    connected personne id"+p.getIdPerson());
        return leaveReqRep.listLeavesByEmp(p.getIdPerson());}
    @Override
    public List<LeaveRequest> getLeavesByMan() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Person p = userRepository.findByUsername(currentPrincipalName).getPersonne();
        System.out.println("\n LEAVEREQSERVICEIMP    connected personne id"+p.getIdPerson());
        return leaveReqRep.listLeavesByMan(p.getIdPerson());
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
        leave.setStatus(LeaveRequestStatus.REJECTED);
        leaveReqRep.save(leave);
    }


    @Override
    public int countLeaves() {
        return leaveReqRep.countLeaves();
    }

    @Autowired
    LeaveProofRepo leaveProofRepo;
    @Override
    public ResponseEntity<Resource> downloadProof(Long idProof) throws  FileNotFoundException {

        String fullPath = leaveProofRepo.findById(idProof).get().getPathToProof();
        System.out.println("full path to file "+ fullPath);
        // Convert the string to a Path object
        Path path = Paths.get(fullPath);

        // Extract the filename from the path
        String filename2 = path.getFileName().toString();

        Path filePath = Paths.get("/Users/hammou/Desktop/WorkSpace/g01/src/main/resources/Files/", filename2);

        // Load file as Resource
        InputStream inputStream = new FileInputStream(filePath.toFile());
        org.springframework.core.io.Resource resource = new InputStreamResource(inputStream);

        // Set Content-Disposition header
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename2);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
