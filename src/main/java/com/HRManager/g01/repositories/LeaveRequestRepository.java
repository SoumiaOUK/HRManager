package com.HRManager.g01.repositories;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {
}
