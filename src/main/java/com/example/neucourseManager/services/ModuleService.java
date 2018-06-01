package com.example.neucourseManager.services;

import com.example.neucourseManager.models.Module;
import com.example.neucourseManager.repositories.CourseRepository;
import com.example.neucourseManager.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//
//createModule
//        creates a module for a course
//        POST /api/course/{cid}/module

//        deleteModule  :  deletes a module by id
//        DELETE /api/module/{id}

//        findAllModules : retrieves all the modules
//        GET /api/module : findModuleById

//        retrieves a module by id
//        GET /api/module/{id}

//        findAllModulesForCourse
//        retrieves all modules for course
//        GET /api/course/{cid}/module

//        updateModule
//        updates a module by id
//        PUT /api/module/{id}

@RestController

public class ModuleService {
    @Autowired
    CourseRepository courseRepository;


    @Autowired
    public ModuleRepository moduleRepository ;

    @PostMapping("/api/course/{cid}/module")
    public Module createModule(@RequestBody Module module) {
        return  moduleRepository.save(module);
    }

    @DeleteMapping("/api/module/{id}")
    public void deleteModule(@PathVariable("id") int id) {

        moduleRepository.deleteById(id);
    }


//        findAllModules : retrieves all the modules
//        GET /api/module : findModuleById
    @GetMapping("/api/module")
    public List<Module> findAllModules(){
        return (List)moduleRepository.findAll();

    }


//        retrieves a module by id
//        GET /api/module/{id}
    @GetMapping("/api/module/{id}")
    public Optional<Module> findModulebyId(@PathVariable("id") int id)
    {
        return moduleRepository.findById(id);
    }



//        findAllModulesForCourse
//        retrieves all modules for [course
//        GET /api/course/{cid}/module
    @GetMapping("/api/course/{cid}/module")
    public ResponseEntity<?> findAllModulesForCourse(@PathVariable("cid") int cid) {

//        Iterable<Module> modules = moduleRepository.findModulesByCourseId(cid);
//
//        return  modules.iterator().hasNext() ? new ResponseEntity<>(modules, HttpStatus.OK) :
//                  new ResponseEntity<>(modules, HttpStatus.NOT_FOUND) ;
        return null;

        }




    @PutMapping("/api/module/{id}")
    public Module updateModule(@PathVariable("id") int id,
                               @RequestBody Module module) {
        Optional<Module> existingModule = moduleRepository.findById(id);

        if (existingModule.isPresent()){
            return  moduleRepository.save(module);
        }

        return null;

    }




}
