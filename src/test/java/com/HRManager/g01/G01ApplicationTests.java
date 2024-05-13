package com.HRManager.g01;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveRequest;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.repositories.EmployeRepository;
import com.HRManager.g01.repositories.LeaveRequestRepository;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.repositories.RoleRepository;
import com.HRManager.g01.security.services.*;
import com.HRManager.g01.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class G01ApplicationTests {

	@Autowired
	EmployeRepository empRepo;

	@Autowired
	EmployeServiceImp empService;
	@Autowired
	PersonServiceImp personeServiceImp;
	/*
	@Test
	public void TestCreatePerson(){
		personeServiceImp.savePerson(new Person("Souumia","ok","soumiaoukhira66@gmail.com","IT","MANAGER"));
	}

	 */
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

	@Autowired
	LeaveReqServiceImp leaveReqService;
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

	/*
	@Test
	public void TestCreateUser(){
		securityService.createUser("souuumia","ouuukhira","soumiaoukhira66@gmail.com");
	}

	 */
	@Autowired
	EmailServiceImp emailSender;

	@Test
	public void TestSendEmail(){
		emailSender.sendEmail("so.oukhira@gmail.com","hello","hey everyone");

		emailSender.sendEmail("soumiaoukhira66@gmail.com","hello","hey everyone");

	}
	//retourner listes des roles
	@Autowired
	RoleServiceImp roleServiceImp;
	@Test
	public void TestListRoles(){
		System.out.println(roleServiceImp.getRoles());

	}
	//get all managers
	@Autowired
	ManagerServiceImp manager;
	@Test
	public void TestListManagers(){
		System.out.println(manager.getAllManagers());
	}
	//ad role to user
	/*
	public void addRoleToUser(String username, Role role){
        User user = userRepository.findByUsername(username);
        if (user != null && role != null) {
            if (user.getRoles() == null) {
                user.setRoles(new ArrayList<>()); // Initialisation de la liste si elle est null
            }
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
	 */
	@Autowired
	AccountServiceImp account;
	@Autowired
	RoleRepository roleRepository;
	@Test
	public void TestAddRoleToUser(){
		Role r= roleRepository.findById("Person").get();
		account.addRoleToUser("P9218_INCHALLAHINCHALLAH",r);
	}


	//for home page we need to count the number of employees and leave requests
	@Test
	public void TestCount(){
		System.out.println("\n count employees = "+empService.countEmployees());
		System.out.println("\n count leave requests = "+leaveReqService.countLeaves());

	}

	//tester creation d'une person "employee " et "manager"
	@Autowired
	ManagerServiceImp managerServiceImp;
	@Test
	public void TestSavePerson(){
		Manager man=managerServiceImp.getManagerById(1);
		//  int soldeConges, String firstName, String lastName, String email, String departement, String role, Manager myManager) {
		Person pEmploye=new Person(12,"khadija","oukhira","soumiaoukhira66@gmail.com","IT","EMPLOYE",man);
		//create employe
		personeServiceImp.savePerson(pEmploye,"EMPLOYE");
		//create manager
		Person pManager=new Person(13,"hammou","oukhira","soumiaoukhira66@gmail.com","IT","MANAGER",man);
		//create employe
		personeServiceImp.savePerson(pManager,"MANAGER");
	}

	@Test
	public void TestgetAllEmployees(){
		List<Employe> employees= empService.getEmployeesByManager();
		employees.forEach(System.out::println);
	}
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

	@Test
	public void TestAddLeave(){
		Date datestart = new Date(2024,1,20);
		Date datefin = new Date(2024,4,20);

		LeaveRequest newleave = new LeaveRequest(datestart,datefin,"sick","sick");
		leaveReqService.saveLeave(newleave);
	}

	@Autowired
	LeaveProofServiceImp leaveProofServiceImp;
	@Test
	public void TestAssignProofToLeave(){
		Integer i = 2;
		Long l = i.longValue();
		leaveProofServiceImp.assignProofToLeave(l,l);
	}





}
