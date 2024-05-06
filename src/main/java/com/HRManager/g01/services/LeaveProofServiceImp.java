package com.HRManager.g01.services;

import com.HRManager.g01.entities.LeaveProof;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.repositories.LeaveProofRepo;
import com.HRManager.g01.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class LeaveProofServiceImp implements LeaveProofService{
    @Autowired
    LeaveProofRepo leaveProofRepo;
    @Autowired
    LeaveReqServiceImp leaveReqServiceImp;

    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    @Override
    public LeaveProof saveLeaveProof(MultipartFile file,Long idLeaveReq) {
        if (file.isEmpty()) {
            System.out.println("Please select a file");
        }

        try {
            // Get the filename and build the local file path
            String fileName = file.getOriginalFilename();
            String directoryPath = "/Users/hammou/Desktop/WorkSpace/g01/src/main/resources/Files/"; // Change the directory as per your requirement

            // Create the directory if it does not exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                System.out.println("directory created");
                directory.mkdirs(); // Create directories including any necessary but nonexistent parent directories
            }

            String filePath = directoryPath + fileName;

            // Save the file to the local file system
            File dest = new File(filePath);
            file.transferTo(dest);
            //actual save on DB
            LeaveRequest leaveRequest=leaveReqServiceImp.readLeaveReq(idLeaveReq) ;
            LeaveProof newProof = leaveProofRepo.save(new LeaveProof(filePath,leaveRequest));
            System.out.println("Successfully Uploaded the following file: " + filePath);
            leaveRequestRepository.assignProofToLeave(newProof.getIdLeaveProof(),leaveRequest.getIdLeave());
            return newProof;

            // Print file details to the console

            // Add success message to the model
        } catch (IOException e) {
            System.out.println("Failed to upload file Exception "+e.getMessage());
        }
return null;

    }

    @Override
    public LeaveProof getLeaveProofById(Long id){

        return leaveProofRepo.findById(id).get();
    }

    @Override
    public String getLeaveProofByLeaveId(Long id) {
        LeaveProof proof = leaveProofRepo.leaveProofByIdReq(id);
        return proof.getPathToProof();
    }

    public void assignProofToLeave(Long idleave , Long idproof){
        System.out.println("id proof"+idproof);
        System.out.println("id leave"+idleave);
        leaveRequestRepository.assignProofToLeave(idleave,idproof);
    }
}
