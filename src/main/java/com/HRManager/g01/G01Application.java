package com.HRManager.g01;

import com.HRManager.g01.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class G01Application {
	/*
	public CommandLineRunner commandLineRunner(AccountService accountService){
		return args -> {
			System.out.println("$$$$$$$$$$$"+accountService.loadUserByUsername("U5668_OUKHIRASOUMIA").getUserId());
		};
	}
*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(G01Application.class, args);
	}
}
