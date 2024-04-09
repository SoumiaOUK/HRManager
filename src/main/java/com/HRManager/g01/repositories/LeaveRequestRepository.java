package com.HRManager.g01.repositories;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {
    @Query(
            value = "select * from leave_request WHERE id_person=?1",
            nativeQuery = true)
    List<LeaveRequest> listLeavesByEmp(long id);

    @Query(
            value = "select * from leave_request WHERE id_manager=?1",
            nativeQuery = true)
    List<LeaveRequest> listLeavesByMan(long id);


}
