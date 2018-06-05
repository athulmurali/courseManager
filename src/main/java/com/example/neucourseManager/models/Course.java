package com.example.neucourseManager.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Course { @Id

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(nullable = false)
    private String title;
    @OneToMany(mappedBy="course")
    private List<Module> modules;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modified;


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
