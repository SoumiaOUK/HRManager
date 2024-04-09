package com.HRManager.g01;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.repositories.EmployeRepository;
import com.HRManager.g01.repositories.LeaveRequestRepository;
import com.HRManager.g01.services.LeaveReqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class G01ApplicationTests {

	@Autowired
	EmployeRepository empRepo;
	@Test
	public void TestCreateEmploye(){
		Employe emp = new Employe();
		empRepo.save(emp);
	}
	/*
	//pourquoi cette méthode m'ajoute une nouvelle ligne dans DB
	@Test
	public void TestUpdateEmploye(){
		Employe emp = empRepo.findById(1L).get();
		emp.setNom("Rabab");
		empRepo.save(emp);
	}

	 */

	@Test
	public void findEmployeById(){
		Employe emp = empRepo.findById(1L).get();
		System.out.println(emp);
	}

	@Test
	public void TestFindAllEmployees(){
		List<Employe> employees = empRepo.findAll();
		employees.forEach(System.out::println);
	}

	@Test
	public void TestDeleteEmployeeByID(){
		empRepo.deleteById(1L);
	}
	@Test
	public void TestDeleteAllEmployees(){
		empRepo.deleteAll();
	}

	@Autowired
	LeaveRequestRepository leaveRepo;
	@Test
	public void TestShowEmpLeaves(){
		List<LeaveRequest> myLeaves= leaveRepo.listLeavesByEmp(3);
		myLeaves.forEach(System.out::println);
	}
	@Test
	public void TestShowLeavesByManager(){
		List<LeaveRequest> managedLeaves=leaveRepo.listLeavesByMan(1);
		managedLeaves.forEach(System.out::println);
	}
	@Autowired
	LeaveReqService leaveReqService;
	@Test
	public void TestAcceptLeave(){
		leaveReqService.acceptLeaveRequest(3);
		System.out.println("done");
	}
}
