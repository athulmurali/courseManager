package com.example.neucourseManager.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course { @Id

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String title;
    @OneToMany(mappedBy="course")
    private List<Module> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
