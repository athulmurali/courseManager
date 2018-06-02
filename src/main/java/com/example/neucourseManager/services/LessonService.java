package com.example.neucourseManager.services;
import com.example.neucourseManager.models.Lesson;
import com.example.neucourseManager.models.Module;
import com.example.neucourseManager.repositories.LessonRepository;
import com.example.neucourseManager.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class LessonService {
    @Autowired
    ModuleRepository moduleRepository;


    @Autowired
    public LessonRepository lessonRepository ;

    @PostMapping("/api/module/{lid}/module")
    public ResponseEntity<?> createLesson(@PathVariable ("lid")  int lid,@RequestBody Lesson lesson) {

        Optional<Module> module   = moduleRepository.findById(lid);
        if ( module.isPresent())
        {
            lesson.setModule(module.get());
            return new ResponseEntity<>(lessonRepository.save(lesson), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping("/api/lesson/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable("id") int id) {
        moduleRepository.deleteById(id);
        return  new ResponseEntity<>(null, HttpStatus.OK);
    }


    //        findAllLessons : retrieves all the modules
//        GET /api/module : findLessonById
    @GetMapping("/api/lesson")
    public ResponseEntity<?> findAllLessons(){
        List<Lesson>  moduleList =  (List<Lesson>)lessonRepository.findAll();

        return new ResponseEntity<>(moduleList,HttpStatus.OK);

    }


    //        retrieves a module by id
//        GET /api/module/{id}
    @GetMapping("/api/lesson/{id}")
    public ResponseEntity<?> findLessonbyId(@PathVariable("id") int id)
    {
        Optional<Lesson> module = lessonRepository.findById(id);
        return module.isPresent()?
                new ResponseEntity<>(module.get(),HttpStatus.OK):
                new ResponseEntity<>(null,HttpStatus.NOT_FOUND);


    }



    //        findAllLessonsForModule
//        retrieves all modules for [module
//        GET /api/module/{lid}/module
    @GetMapping("/api/module/{mid}/lesson")
    public ResponseEntity<?> findAllLessonsForModule(@PathVariable("mid") int mid) {
        System.out.println("param value : " + mid);

        Iterable<Lesson> modules = lessonRepository.findLessonsByModuleId(mid);

        return  modules.iterator().hasNext() ?
                new ResponseEntity<>(modules, HttpStatus.OK) :
                new ResponseEntity<>(modules, HttpStatus.NOT_FOUND) ;

    }




    // Note : module_id of a module is not modifiable
    @PutMapping("/api/lesson/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable("id") int id,
                                          @RequestBody Lesson module) {
        Optional<Lesson> existingLesson = lessonRepository.findById(id);

        if (existingLesson.isPresent()){
            Lesson editedLesson = existingLesson.get();
            editedLesson.setTitle(module.getTitle());
            lessonRepository.save(editedLesson);
            return new ResponseEntity<>(editedLesson,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }




}
