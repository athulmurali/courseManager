package com.example.neucourseManager.services;

import com.example.neucourseManager.models.Course;
import com.example.neucourseManager.models.Module;
import com.example.neucourseManager.repositories.CourseRepository;
import com.example.neucourseManager.repositories.ModuleRepository;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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
    public ResponseEntity<?> createModule(@PathVariable ("cid")  int cid,@RequestBody Module module) {

        Optional<Course> course   = courseRepository.findById(cid);
        if ( course.isPresent())
        {
            module.setCourse(course.get());
            module = moduleRepository.save(module);
            return new ResponseEntity<>(moduleRepository.save(module), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping("/api/module/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable("id") int id) {
        moduleRepository.deleteById(id);
        return  new ResponseEntity<>(null, HttpStatus.OK);
    }


//        findAllModules : retrieves all the modules
//        GET /api/module : findModuleById
    @GetMapping("/api/module")
    public ResponseEntity<?> findAllModules(){
        List<Module>  moduleList =  (List)moduleRepository.findAll();

        return new ResponseEntity<>(moduleList,HttpStatus.OK);

    }


//        retrieves a module by id
//        GET /api/module/{id}
    @GetMapping("/api/module/{id}")
    public ResponseEntity<?> findModulebyId(@PathVariable("id") int id)
    {
        Optional<Module> module = moduleRepository.findById(id);
        return module.isPresent()?
                new ResponseEntity<>(module.get(),HttpStatus.OK):
                new ResponseEntity<>(null,HttpStatus.NOT_FOUND);


    }



//        findAllModulesForCourse
//        retrieves all modules for [course
//        GET /api/course/{cid}/module
    @GetMapping("/api/course/{cid}/module")
    public ResponseEntity<?> findAllModulesForCourse(@PathVariable("cid") int cid) {
        System.out.println("param value : " + cid);

        Iterable<Module> modules = moduleRepository.findModulesByCourseId(cid);

        return  modules.iterator().hasNext() ?
                new ResponseEntity<>(modules, HttpStatus.OK) :
                new ResponseEntity<>(modules, HttpStatus.NOT_FOUND) ;

        }




        // Note : course_id of a module is not modifiable
    @PutMapping("/api/module/{id}")
    public ResponseEntity<?> updateModule(@PathVariable("id") int id,
                               @RequestBody Module module) {
        Optional<Module> existingModule = moduleRepository.findById(id);

        if (existingModule.isPresent()){
             Module editedModule = existingModule.get();
            editedModule.setTitle(module.getTitle());
             moduleRepository.save(editedModule);
             return new ResponseEntity<>(editedModule,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }




}
