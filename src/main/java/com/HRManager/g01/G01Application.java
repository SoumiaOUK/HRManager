package com.HRManager.g01;

import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.entities.Manager;
import com.HRManager.g01.entities.Person;
import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.services.AccountService;
import com.HRManager.g01.security.services.RoleService;
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
	/*
	@Bean
	public CommandLineRunner demoRole(RoleService roleService) {
		return args -> {

			//accountService.CreateUser("admin","123","admin@gmail.com","123");
			//create account

			//create role
			Role  roleEmploye = new Role("EMPLOYE");
			Role  roleManager = new Role("MANAGER");
			roleService.saveRole(roleEmploye);
			roleService.saveRole(roleManager);
		};
	}
	@Bean
	public CommandLineRunner demoPerson(PersonServiceImp personServiceImp) {
		return args -> {

			//accountService.CreateUser("admin","123","admin@gmail.com","123");
			//create account
			Person manager1 = new Person(15,"soumia","ok","soumiaoukhira66@gmail","IT","MANAGER",null);
			personServiceImp.savePerson(manager1,"MANAGER");

		};
	}

	 */



}
