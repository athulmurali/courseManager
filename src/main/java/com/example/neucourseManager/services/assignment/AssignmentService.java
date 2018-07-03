package com.example.neucourseManager.services.assignment;

import com.example.neucourseManager.models.Topic;
import com.example.neucourseManager.models.assignment.Assignment;
import com.example.neucourseManager.repositories.TopicRepository;
import com.example.neucourseManager.repositories.assignment.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    TopicRepository topicRepository;



//      GET
//      /api/assignment
//      Returns an array of all assignments

    @GetMapping("/api/assignment")
    public List<Assignment> findAllAssignments() {
        System.out.println("Getting all assignments   :  ");

        List<Assignment> assignmentsList = new ArrayList<>();
        assignmentsList  = (List<Assignment>)assignmentRepository.findAll();
        return assignmentsList;
    }


//      GET
//      /api/assignment/:aid
//      Returns an assignment whose id is aid

    @GetMapping("/api/assignment/{aid}")
    public Assignment findAssignmentById(@PathVariable("aid") int aid) {
        System.out.println("Getting  assignments  by assignmentId  :  " + aid);

        Optional<Assignment> assignment = assignmentRepository.findById(aid);
        if (assignment.isPresent())
            return  assignment.get();
        return null;

    }


//      GET
//      /api/topic/:tid/assignment
//      Returns an array of assignments for a given topic

    @GetMapping("/api/topic/{tid}/assignment")
    public  List<Assignment> findAssignmentByTopicId(@PathVariable("tid") int tid) {
        System.out.println("Getting assignments by Topic id   :  " + tid);
        List<Assignment>  assignmentList= (List<Assignment>) assignmentRepository.findAll();
        assignmentList =  assignmentList.stream().
                filter(assignmentObj -> assignmentObj.getTopic().getId() ==tid).
                collect(Collectors.toList());
        return assignmentList;
    }


    //            POST
///api/topic/:tid/assignment
//    Creates a new assignment for a given topic
    @PostMapping("/api/topic/{tid}/assignment")
    public ResponseEntity<?> createAssignment(@PathVariable("tid") int tid,
                           @RequestBody Assignment assignment){
        System.out.println("Creating assignment");
        System.out.println(assignment);



        Optional<Topic> topic =  topicRepository.findById(tid);
        if (topic.isPresent())
        {
            assignment.setTopic(topic.get());
            return new ResponseEntity(assignmentRepository.save(assignment), HttpStatus.OK);

        }

        else return new ResponseEntity(HttpStatus.NOT_FOUND);

    }



//            DELETE
///api/assignment/:aid
//    Deletes an assignment whose id is aid


    @DeleteMapping("/api/assignment/{aid}")
    public ResponseEntity<?> deleteAssignmentById(@PathVariable("aid") int aid) {
        System.out.println("Deleting  assignment  by assignmentId  :  " + aid);
        try{
            assignmentRepository.deleteById(aid);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.GONE);

        }
    }
}
