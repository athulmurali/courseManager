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
}
