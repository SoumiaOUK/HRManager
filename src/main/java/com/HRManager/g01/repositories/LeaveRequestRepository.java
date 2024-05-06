package com.HRManager.g01.repositories;
import com.HRManager.g01.entities.LeaveRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Transactional
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {
    @Query(
            value = "select * from leave_request WHERE id_person=?1",
            nativeQuery = true)
    List<LeaveRequest> listLeavesByEmp(long id);

    @Query(
            value = "select * from leave_request WHERE id_manager=?1",
            nativeQuery = true)
    List<LeaveRequest> listLeavesByMan(long id);


    @Query(
            value = "select count(*) from leave_request",
            nativeQuery = true)
    int countLeaves();

    @Modifying
    @Query(
            value = "UPDATE leave_request SET leave_proof_id = :idProof WHERE id_leave = :idLeave",
            nativeQuery = true)
    void assignProofToLeave(@Param("idProof") Long idProof, @Param("idLeave") Long idLeave);
}