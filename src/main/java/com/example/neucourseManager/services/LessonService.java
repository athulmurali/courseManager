package com.example.neucourseManager.services;
import com.example.neucourseManager.models.Lesson;
import com.example.neucourseManager.models.Module;
import com.example.neucourseManager.repositories.CourseRepository;
import com.example.neucourseManager.repositories.LessonRepository;
import com.example.neucourseManager.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



//                createLesson
//                creates a lesson for a module
//                POST /api/course/{cid}/module/{mid}/lesson
//
//                deleteLesson
//                deletes a lesson by id
//                DELETE /api/lesson/{id}
//
//                findAllLessons
//                retrieves all the lessons
//                GET /api/lesson
//
//                findLessonById
//                retrieves a lesson by id
//                GET /api/lesson/{id}
//
//                findAllLessonsForModule
//                retrieves all lessons for module
//                GET /api/course/{cid}/module/{mid}/lesson
//
//                updateLesson
//                updates a lesson by id
//                PUT /api/lesson/{id}


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class LessonService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;


    @Autowired
    public LessonRepository lessonRepository ;

    //   POST /api/course/{cid}/module/{mid}/lesson
    @PostMapping("/api/course/{cid}/module/{mid}/lesson")
    public ResponseEntity<?> createLesson(@PathVariable ("cid")  int cid,
                                          @PathVariable ("mid")  int mid,
                                          @RequestBody Lesson lesson) {
        Module module = moduleRepository.findById(mid).get();

        if ( module != null &&  module.getCourse().getId() == cid)
        {
            System.out.println("Module belongs to the Course....");

            lesson.setModule(module);
            return new ResponseEntity<>(lessonRepository.save(lesson), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
    }

    //                deleteLesson
//                deletes a lesson by id
//                DELETE /api/lesson/{id}
    @DeleteMapping("/api/lesson/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable("id") int id) {
        lessonRepository.deleteById(id);
        return  new ResponseEntity<>(null, HttpStatus.OK);
    }


    //                findAllLessons
//                retrieves all the lessons
//                GET /api/lesson
    @GetMapping("/api/lesson")
    public ResponseEntity<?> findAllLessons(){
        List<Lesson>  moduleList =  (List<Lesson>)lessonRepository.findAll();

        return new ResponseEntity<>(moduleList,HttpStatus.OK);

    }


    //                findLessonById
//                retrieves a lesson by id
//                GET /api/lesson/{id}
    @GetMapping("/api/lesson/{id}")
    public ResponseEntity<?> findLessonById (@PathVariable("id") int id)
    {
        Optional<Lesson> module = lessonRepository.findById(id);
        return module.isPresent()?
                new ResponseEntity<>(module.get(),HttpStatus.OK):
                new ResponseEntity<>(null,HttpStatus.NOT_FOUND);


    }



    //                findAllLessonsForModule
//                retrieves all lessons for module
//                GET /api/course/{cid}/module/{mid}/lesson
//
    @GetMapping("/api/course/{cid}/module/{mid}/lesson")
    public ResponseEntity<?> findAllLessonsForModule(@PathVariable("cid") int cid,
                                                    @PathVariable("mid") int mid) {

        Module module = moduleRepository.findById(mid).get();

        if ( module != null &&  module.getCourse().getId() == cid)
        {
            System.out.println("Module belongs to the Course....");

            return new ResponseEntity<>(lessonRepository.findLessonsByModuleId(mid), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
    }




    // Note : module_id of a module is not modifiable
    @PutMapping("/api/lesson/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable("id") int id,
                                          @RequestBody Lesson lesson) {
        Optional<Lesson> existingLesson = lessonRepository.findById(id);

        if (existingLesson.isPresent()){
            Lesson editedLesson = existingLesson.get();
            editedLesson.setTitle(lesson.getTitle());
            lessonRepository.save(editedLesson);
            return new ResponseEntity<>(editedLesson,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }

}
