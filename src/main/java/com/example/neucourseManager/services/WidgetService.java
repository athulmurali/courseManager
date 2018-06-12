package com.example.neucourseManager.services;

import com.example.neucourseManager.models.Topic;
import com.example.neucourseManager.models.Topic;
import com.example.neucourseManager.models.Widget;
import com.example.neucourseManager.repositories.CourseRepository;
import com.example.neucourseManager.repositories.TopicRepository;
import com.example.neucourseManager.repositories.TopicRepository;
import com.example.neucourseManager.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



//                createWidget
//                creates a widget for a topic
//                POST /api/course/{cid}/lesson/{mid}/topic/{lid}/widget
//
//                deleteWidget
//                deletes a widget by id
//                DELETE /api/widget/{id}
//
//                findAllWidgets
//                retrieves all the widgets
//                GET /api/widget
//
//                findWidgetById
//                retrieves a widget by id
//                GET /api/widget/{id}
//
//                findAllWidgetsForTopic
//                retrieves all widgets for topic
//                GET /api/course/{cid}/lesson/{mid}/topic/{lid}/widget
//
//                updateWidget
//                updates a widget by id
//                PUT/api/widget/{id}


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class WidgetService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TopicRepository topicRepository;


    @Autowired
    public WidgetRepository widgetRepository ;

//    @PostMapping("/api/course/{cid}/lesson/{mid}/topic/{lid}/widget")
//    public ResponseEntity<?> createWidget(@PathVariable ("cid")   int cid,
//                                          @PathVariable ("mid")  int mid,
//                                         @PathVariable ("lid")   int lid,
//
//                                         @RequestBody Widget widget) {
//        Topic topic = topicRepository.findById(lid).get();
//
//        if ( topic != null &&  topic.getLesson().getId() == mid)
//        {
//            System.out.println("Widget belongs to the Topic....");
//
//            widget.setTopic(topic);
//            return new ResponseEntity<>(widgetRepository.save(widget), HttpStatus.OK) ;
//        }
//        else
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
//    }

//
//    @DeleteMapping("/api/widget/{id}")
//    public ResponseEntity<?> deleteWidget(@PathVariable("id") int id) {
//        widgetRepository.deleteById(id);
//        return  new ResponseEntity<>(null, HttpStatus.OK);
//    }


    //                findAllWidgets
//                retrieves all the widgets
//                GET /api/widget
    @GetMapping("/api/widget")
    public ResponseEntity<?> findAllWidgets(){
        List<Widget>  widgetList =  (List<Widget>)widgetRepository.findAll();

        return new ResponseEntity<>(widgetList,HttpStatus.OK);

    }

//
////                findWidgetById
////                retrieves a widget by id
////                GET /api/widget/{id}
//    @GetMapping("/api/widget/{id}")
//    public ResponseEntity<?> findWidgetById (@PathVariable("id") int id)
//    {
//        Optional<Widget> widget = widgetRepository.findById(id);
//        return widget.isPresent()?
//                new ResponseEntity<>(widget.get(),HttpStatus.OK):
//                new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//    }


//
//    @GetMapping("/api/course/{cid}/lesson/{mid}/topic/{lid}/widget")
//    public ResponseEntity<?> findAllWidgetsForTopic(@PathVariable("cid") int cid,
//                                                    @PathVariable("mid") int mid,
//                                                    @PathVariable("tid") int tid) {
//
//        Topic topic = topicRepository.findById(tid).get();
//
//        if ( topic != null &&  topic.getLesson().getId() == mid)
//        {
//            System.out.println("Topic belongs to the Course....");
//
//            return new ResponseEntity<>(widgetRepository.findWidgetsByTopicId(tid), HttpStatus.OK) ;
//        }
//        else
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND) ;
//    }
//
//

//
//    // Note : widget_id of a widget must not modifiable
//    @PutMapping("/api/widget/{id}")
//    public ResponseEntity<?> updateWidget(@PathVariable("id") int id,
//                                          @RequestBody Widget widget) {
//        Optional<Widget> existingWidget = widgetRepository.findById(id);
//
//        if (existingWidget.isPresent()){
//            Widget editedWidget = existingWidget.get();
//            editedWidget.setTitle(widget.getTitle());
//            widgetRepository.save(editedWidget);
//            return new ResponseEntity<>(editedWidget,HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//
//    }

}
