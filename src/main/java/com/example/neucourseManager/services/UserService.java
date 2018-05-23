package com.example.neucourseManager.services;

import com.example.neucourseManager.models.User;
import com.example.neucourseManager.repositories.UserRepository;
import com.example.neucourseManager.utilities.email.Emailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) throws IOException, MessagingException {
        User returnedUser = repository.save(user);
        if (returnedUser.getEmail()!= null)
        {
            Emailer emailer = new Emailer();
            emailer.sendEmail(user.getEmail(), "NEU Course Manager: Registration","You have been registered!");
        }

        return returnedUser;
    }

	@PostMapping("/api/login")
	public ResponseEntity<Object> login(@RequestBody User user) {
		System.out.println("\n\n\n\n\n " + user.getUsername() + "  " + user.getPassword());
		System.out.println(repository.findUserByCredentials(user.getUsername(), user.getPassword()));
		if(! ((List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword())).isEmpty())
		{
		    System.out.println("User found!");
		    // send token
            return ResponseEntity.status(HttpStatus.OK).build();
		}
		else {
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



		if(data.isPresent()) {
			User user = data.get();
            user.setUsername(newUser.getUsername());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(newUser.getPassword());
            user.setRole(newUser.getRole());

            System.out.println("firstName..."   +  user.getFirstName());
            System.out.println("lastName..."    +  user.getLastName());
            System.out.println("role..."        +  user.getRole());

            repository.save(user);




            System.out.println("put complete........." +  userId);
			return user;
		}
		return null;
	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
}
