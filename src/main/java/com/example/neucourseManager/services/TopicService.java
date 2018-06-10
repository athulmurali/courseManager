package com.example.neucourseManager.services;
import com.example.neucourseManager.models.Topic;
import com.example.neucourseManager.models.Lesson;
import com.example.neucourseManager.repositories.CourseRepository;
import com.example.neucourseManager.repositories.TopicRepository;
import com.example.neucourseManager.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



//                createTopic
//                creates a topic for a lesson
//                POST /api/course/{cid}/module/{mid}/lesson/{lid}/topic
//
//                deleteTopic
//                deletes a topic by id
//                DELETE /api/topic/{id}
//
//                findAllTopics
//                retrieves all the topics
//                GET /api/topic
//
//                findTopicById
//                retrieves a topic by id
//                GET /api/topic/{id}
//
//                findAllTopicsForLesson
//                retrieves all topics for lesson
//                GET /api/course/{cid}/module/{mid}/lesson/{lid}/topic
//
//                updateTopic
//                updates a topic by id
//                PUT/api/topic/{id}


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class TopicService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LessonRepository lessonRepository;


    @Autowired
    public TopicRepository topicRepository ;

    @PostMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
    public ResponseEntity<?> createTopic(@PathVariable ("cid")   int cid,
                                          @PathVariable ("mid")  int mid,
                                         @PathVariable ("lid")   int lid,

                                         @RequestBody Topic topic) {
        Lesson lesson = lessonRepository.findById(lid).get();

        if ( lesson != null &&  lesson.getModule().getId() == mid)
        {
            System.out.println("Topic belongs to the Lesson....");

            topic.setLesson(lesson);
            return new ResponseEntity<>(topicRepository.save(topic), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
    }


    @DeleteMapping("/api/topic/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable("id") int id) {
        topicRepository.deleteById(id);
        return  new ResponseEntity<>(null, HttpStatus.OK);
    }


    //                findAllTopics
//                retrieves all the topics
//                GET /api/topic
    @GetMapping("/api/topic")
    public ResponseEntity<?> findAllTopics(){
        List<Topic>  lessonList =  (List<Topic>)topicRepository.findAll();

        return new ResponseEntity<>(lessonList,HttpStatus.OK);

    }


//                findTopicById
//                retrieves a topic by id
//                GET /api/topic/{id}
    @GetMapping("/api/topic/{id}")
    public ResponseEntity<?> findTopicById (@PathVariable("id") int id)
    {
        Optional<Topic> lesson = topicRepository.findById(id);
        return lesson.isPresent()?
                new ResponseEntity<>(lesson.get(),HttpStatus.OK):
                new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }



    @GetMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
    public ResponseEntity<?> findAllTopicsForLesson(@PathVariable("cid") int cid,
                                                    @PathVariable("mid") int mid,
                                                    @PathVariable("lid") int lid) {

        Lesson lesson = lessonRepository.findById(lid).get();

        if ( lesson != null &&  lesson.getModule().getId() == mid)
        {
            System.out.println("Lesson belongs to the Course....");

            return new ResponseEntity<>(topicRepository.findTopicsByLessonId(lid), HttpStatus.OK) ;
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
    }




    // Note : topic_id of a topic must not modifiable
    @PutMapping("/api/topic/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable("id") int id,
                                          @RequestBody Topic topic) {
        Optional<Topic> existingTopic = topicRepository.findById(id);

        if (existingTopic.isPresent()){
            Topic editedTopic = existingTopic.get();
            editedTopic.setTitle(topic.getTitle());
            topicRepository.save(editedTopic);
            return new ResponseEntity<>(editedTopic,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }

}
