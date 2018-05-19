package com.example.courseManager.services;

import com.example.courseManager.models.User;
import com.example.courseManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}

	@PostMapping("/api/login")
	public List<User> login(@RequestBody User user) {
		return (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
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
