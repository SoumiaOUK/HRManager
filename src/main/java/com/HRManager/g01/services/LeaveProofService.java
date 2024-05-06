package com.HRManager.g01.services;

import com.HRManager.g01.entities.LeaveProof;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface LeaveProofService {
    LeaveProof saveLeaveProof(MultipartFile file, Long idLeaveReq);
    LeaveProof getLeaveProofById(Long id);
    String getLeaveProofByLeaveId(Long id);

}
