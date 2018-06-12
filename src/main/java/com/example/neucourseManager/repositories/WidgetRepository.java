package com.example.neucourseManager.repositories;


import com.example.neucourseManager.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WidgetRepository
        extends CrudRepository<Widget, Integer>{

//
//    @Query("SELECT t FROM Topic t WHERE t.lesson.id=:lid")
//    Iterable<Topic> findTopicsByLessonId(@Param("lid") int lid);
}
