package com.HRManager.g01.security.services;

import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    //User createUser(String username, String password, String confirmPassword);
    User createUser(String firstName,String lastName,String email);
    Role createRole(String role);
    void addRoleToUser(String username, String role);

    void removeRoleFromUser(String username, String role);
    User loadUserByUsername(String username);

    String usernameGenerator(String firstName,String lastName);

    String generateRandomPassword();
    //List<String> listRole(User user);

    //void sendPasswordByEmail(String password,String mail);
}
