package com.HRManager.g01.repositories;
import com.HRManager.g01.entities.LeaveProof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveProofRepo extends JpaRepository<LeaveProof,Long> {
    @Query(
            value = "select * from leave_proof WHERE leaveReqId=?1",
            nativeQuery = true)
    LeaveProof leaveProofByIdReq(long id);
}
