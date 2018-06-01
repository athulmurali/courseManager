package com.example.neucourseManager.repositories;


import org.springframework.data
        .repository.CrudRepository;
import com.example.neucourseManager.models.Module;

public interface ModuleRepository
        extends CrudRepository<Module, Integer>{}
