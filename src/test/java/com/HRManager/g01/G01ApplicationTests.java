package com.HRManager.g01;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.EmployeRepository;
import com.HRManager.g01.repositories.LeaveRequestRepository;
import com.HRManager.g01.security.services.AccountService;
import com.HRManager.g01.security.services.AccountServiceImp;
import com.HRManager.g01.security.services.EmailServiceImp;
import com.HRManager.g01.services.LeaveReqService;
import com.HRManager.g01.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class G01ApplicationTests {

	@Autowired
	EmployeRepository empRepo;
	@Autowired
	PersonService personeServiceImp;
	@Test
	public void TestCreatePerson(){
		personeServiceImp.savePerson(new Person("Souumia","ok","soumiaoukhira66@gmail.com","IT","MANAGER"));
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


	@Autowired
	AccountServiceImp securityService;
	@Test
	public void TestUsernameGenerator(){
		System.out.println(securityService.usernameGenerator("soumia","Oukhira"));

	}
	@Test
	public void TestPasswordGenerator(){
		System.out.println(securityService.generateRandomPassword());;
	}

	@Test
	public void TestCreateUser(){
		securityService.createUser("souuumia","ouuukhira","soumiaoukhira66@gmail.com");
	}
	@Autowired
	EmailServiceImp emailSender;

	@Test
	public void TestSendEmail(){
		emailSender.sendEmail("so.oukhira@gmail.com","hello","hey everyone");

		emailSender.sendEmail("soumiaoukhira66@gmail.com","hello","hey everyone");

	}
}
