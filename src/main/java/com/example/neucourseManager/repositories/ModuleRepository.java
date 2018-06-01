package com.example.neucourseManager.repositories;


import com.example.neucourseManager.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.example.neucourseManager.models.Module;

public interface ModuleRepository
        extends CrudRepository<Module, Integer>{


    @Query("SELECT m FROM Module m WHERE m.course=:course_id")
    Iterable<Module> findModulesByCourseId(@Param("course_id") int course_id);
}
