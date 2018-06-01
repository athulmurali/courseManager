package com.example.neucourseManager.services;

import com.example.neucourseManager.models.Module;
import com.example.neucourseManager.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ModuleService {

    @Autowired
    ModuleRepository repository ;
}
