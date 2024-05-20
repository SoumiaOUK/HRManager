package com.HRManager.g01;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.LeaveType;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.services.AccountService;
import com.HRManager.g01.security.services.RoleService;
import com.HRManager.g01.services.LeaveTypeService;
import com.HRManager.g01.services.LeaveTypeServiceImp;
import com.HRManager.g01.services.ManagerServiceImp;
import com.HRManager.g01.services.PersonServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class G01Application {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(G01Application.class, args);
	}

	@Bean
	public CommandLineRunner fillTables(RoleService roleService,LeaveTypeServiceImp leaveTypeServiceImp,ManagerServiceImp managerService) {
		return args -> {
			System.out.println("roles adding");
			Role  roleEmploye = new Role("EMPLOYE");
			Role  roleManager = new Role("MANAGER");
			roleService.saveRole(roleEmploye);
			roleService.saveRole(roleManager);
			//leavetypes
			System.out.println("leavetypes adding");
			LeaveType Sick = new LeaveType("sick","sick",10);
			LeaveType Maternity = new LeaveType("sick","sick",12);
			LeaveType Annual = new LeaveType("sick","sick",30);
			leaveTypeServiceImp.saveLeaveType(Sick);
			leaveTypeServiceImp.saveLeaveType(Maternity);
			leaveTypeServiceImp.saveLeaveType(Annual);
			//managers
			System.out.println("manager adding");
			Manager man = new Manager(12,"soumia","ouk","soumiaoukhira66@gmail.com","IT","MANAGER");
			Manager manager = managerService.saveManager(man);

		};
	}
	/*
	@Bean
	public CommandLineRunner demoLeaveType(LeaveTypeServiceImp leaveTypeServiceImp){
		return args -> {
			System.out.println("leavetypes adding");
			LeaveType Sick = new LeaveType("sick","sick",10);
			LeaveType Maternity = new LeaveType("sick","sick",12);
			LeaveType Annual = new LeaveType("sick","sick",30);
			leaveTypeServiceImp.saveLeaveType(Sick);
			leaveTypeServiceImp.saveLeaveType(Maternity);
			leaveTypeServiceImp.saveLeaveType(Annual);
		};
	}

	@Bean
	public CommandLineRunner demoManager(ManagerServiceImp managerService) {
		return args -> {
			System.out.println("manager adding");
			Manager man = new Manager(12,"soumia","ouk","soumiaoukhira66@gmail.com","IT","MANAGER");
			Manager manager = managerService.saveManager(man);



		};
	}

	 */






}
