package com.example.courseManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.courseManager.models.Hello;

public interface HelloRepository
	extends CrudRepository<Hello, Integer> {
}
