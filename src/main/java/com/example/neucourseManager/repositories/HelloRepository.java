package com.example.neucourseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.neucourseManager.models.Hello;

public interface HelloRepository
	extends CrudRepository<Hello, Integer> {
}
