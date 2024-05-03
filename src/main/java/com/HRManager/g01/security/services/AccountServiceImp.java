package com.HRManager.g01.security.services;


import com.HRManager.g01.security.entities.Role;
import com.HRManager.g01.security.entities.User;
import com.HRManager.g01.security.repositories.RoleRepository;
import com.HRManager.g01.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImp implements AccountService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public Role createRole(String role){
        Role newRole = roleRepository.findById(role).orElse(null);
        newRole= Role.builder().role(role).build();
        return roleRepository.save(newRole);
    }

    @Override
    public void addRoleToUser(String username, String role){
        User user = userRepository.findByUsername(username);
        Role hisRole = roleRepository.findById(role).orElse(null);
        user.getRoles().add(hisRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role){
        User user = userRepository.findByUsername(username);
        Role hisRole = roleRepository.findById(role).orElse(null);
        user.getRoles().remove(hisRole);
    }


    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    @Override
    public String usernameGenerator(String firstName, String lastName) {


            // Generate random letter
            char randomLetter = LETTERS.charAt(RANDOM.nextInt(LETTERS.length()));

            // Generate random number (between 1000 and 9999)
            int randomNumber = RANDOM.nextInt(9000) + 1000;

            // Combine all parts to form the username
            String username = String.format("%c%d%s%s%s", randomLetter, randomNumber,"_", lastName, firstName);

            // Optionally, you can lowercase the username
            return username.toUpperCase();


    }
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static Random random = new Random();


    public String generateRandomPassword() {
        int length=8;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomCharIndex = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            sb.append(DATA_FOR_RANDOM_STRING.charAt(randomCharIndex));
        }
        return sb.toString();
    }



@Autowired EmailServiceImp sendEmail;
    @Override
    public User createUser(String firstName, String lastName,String email) {
        String username=usernameGenerator(firstName,lastName);
        //verify that the username doesn't exist because it's unique
        if(userRepository.findByUsername(username)!=null) throw new RuntimeException("Error : username already exists!");

        String password= generateRandomPassword();

        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        System.out.println(user.toString());
        try{
            sendEmail.sendEmail(email,"Your Credentials For HRManager",username+" "+password);
        }catch (Exception e) {
            System.out.println("ERROR SENDING EMAIL CREDENTIALS : " + e.getMessage() + "USERNAME : " + username + "PASSWORD : " + password);
        }
        return userRepository.save(user);
    }



    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("username not found");
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public List<String> listRole(User user){
        return user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
    }
    /*

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    */

    /*



    public void sendPasswordByEmail(String email, String password) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Your New Account Password");
        mailMessage.setText("Your password is: " + password);

        javaMailSender.send(mailMessage);
    }
     */

      /*
    @Override
    public User createUser(String username, String password, String confirmPassword) {
        //verify that the username does't exist because it's unique
        if(userRepository.findByUsername(username)!=null) throw new RuntimeException("Error : username already exists!");
        //password confirmation is correct
        if(!password.equals(confirmPassword)) throw new RuntimeException("Error : the passwords are not the same!");
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        return userRepository.save(user);
    }

     */


}
