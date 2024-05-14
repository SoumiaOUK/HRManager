package com.HRManager.g01.security;
import com.HRManager.g01.entities.Employe;
import com.HRManager.g01.security.services.AccountServiceImp;
import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
   private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                //.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authCustomizer -> authCustomizer
                                .requestMatchers(
                                        "listManagedBonus","listBonusByEmp","createBonus","saveBonus",
                                        "createEmploye","saveEmploye","employeList","deleteEmploye","showEmploye", "updateEmploye",
                                        "listManagedLeaves","acceptLeave","rejectLeave","listLeavesByEmp",
                                        "createLeaveType","saveLeaveType","showLeaveType","updateLeaveType","deleteLeaveType",
                                        "showProfile",
                                        "listManagedTasks","listEmployeTasks","createTask","saveTask",
                                        "createPerson","savePerson","PersonList","deletePerson","showPerson"
                                        ).hasRole("MANAGER")
                                .requestMatchers("/login","/webjars/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                )
                .exceptionHandling(e ->e.accessDeniedPage("/accessDenied"))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Autowired
    AccountServiceImp accountServiceImp;
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.HRManager.g01.security.entities.User userEntity = accountServiceImp.loadUserByUsername(username);
            if (userEntity == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            List<String> roleNames = accountServiceImp.listRole(userEntity);

            return org.springframework.security.core.userdetails.User.builder()
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .roles(roleNames.toArray(new String[0]))
                    .build();
        };
    }
}

