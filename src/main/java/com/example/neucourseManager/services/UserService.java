package com.example.neucourseManager.services;

import com.example.neucourseManager.models.User;
import com.example.neucourseManager.repositories.UserRepository;
import com.example.neucourseManager.utilities.email.Emailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@RestController
public class UserService {

    private Emailer emailer = new Emailer();
    private final String resetPassPath = "/jquery/components/user-password-reset/user-password-reset.html";

    @Value("${serverURL}")
    private String serverURL;

//    private Environment env;
//    private String serverURL = env.getRequiredProperty("SERVERURL");

    @Autowired
    UserRepository repository;


    public UserService() throws IOException {
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        repository.deleteById(id);
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) throws IOException, MessagingException {
        User returnedUser = repository.save(user);
        if (returnedUser.getEmail() != null) {
            Emailer emailer = new Emailer();
            emailer.sendEmail(user.getEmail(), "NEU Course Manager: Registration", "You have been registered!");
        }

        return returnedUser;
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        System.out.println("\n\n\n\n\n " + user.getUsername() + "  " + user.getPassword());
        System.out.println(repository.findUserByCredentials(user.getUsername(), user.getPassword()));
        if (!((List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword())).isEmpty()) {
            System.out.println("User found!");
            // send token
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        }
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) repository.findAll();
    }

    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
        Optional<User> data = repository.findById(userId);
        System.out.println(newUser);


        if (data.isPresent()) {
            User user = data.get();
            user.setUsername(newUser.getUsername());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(newUser.getPassword());
            user.setRole(newUser.getRole());

            System.out.println("firstName..." + user.getFirstName());
            System.out.println("lastName..." + user.getLastName());
            System.out.println("role..." + user.getRole());

            repository.save(user);


            System.out.println("put complete........." + userId);
            return user;
        }
        return null;
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        Optional<User> data = repository.findById(userId);
        if (data.isPresent()) {
            return data.get();
        }
        return null;
    }

    @PostMapping("/api/user/forgotPassword")
    /**
     * Adds a reset token  to the database of the user
     */
    public ResponseEntity<Object> forgotPassword(@RequestBody User user) throws IOException, MessagingException {


        String email = user.getEmail();
        System.out.println("\n\n\n*******forgot password - post*********");
        System.out.println("emailId :" + email);
        List<User> users = (List<User>) repository.findUserByEmail(email);

        if (users.isEmpty()) {
            System.out.println("No user account with the given email exists!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } else {
            User modifiedUser = users.get(0);
            String reset_token = UUID.randomUUID().toString();
            modifiedUser.setReset_token(reset_token);
            repository.save(modifiedUser);
            System.out.println("reset token has been updated for the user");


            String param = "?" + "reset_token=" + reset_token;

            String urlWithToken = serverURL + resetPassPath + param;
            String message = "Please click the link to reset your password! " + urlWithToken;


            emailer.sendEmail(modifiedUser.getEmail(), "NEU Course Manager :Password Reset Link",
                    message);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

    }


    @PostMapping("/api/user/resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody User user) throws MessagingException {

        String password = user.getPassword();
        String reset_token = user.getReset_token();

        System.out.println("reset token received :"  + reset_token);
        System.out.println("new password received :" + password);


        List<User> users = (List<User>) repository.findUserByResetToken(reset_token);
        if (!users.isEmpty()) {

            User modifiedUser = users.get(0);
            modifiedUser.setReset_token("");
            modifiedUser.setPassword(password);
            repository.save(modifiedUser);
            System.out.println("Password successfully reset!");

            emailer.sendEmail(modifiedUser.getEmail(),
                    "Password Changed!",
                    "Your password has been successfully changed!");

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();


    }


    @PostMapping("/api/isEmailAvailable")
    public ResponseEntity<Object> isEmailAvailable(@RequestBody User user){
         if (repository.findUserByEmail(user.getEmail()).iterator().hasNext())
             // if the list is not empty it has next in iterator;
             // then  it means the email is already taken
             return ResponseEntity.status(HttpStatus.CONFLICT).build();

         else return ResponseEntity.status(HttpStatus.OK).build();

    }


}
