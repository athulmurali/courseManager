package com.example.courseManager.services;

import com.example.courseManager.models.Hello;
import com.example.courseManager.repositories.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {
	@Autowired
    HelloRepository repository;

	@GetMapping("/api/hello")
	public Iterable<Hello> findAllHellos() {
		return repository.findAll();
	}

}
