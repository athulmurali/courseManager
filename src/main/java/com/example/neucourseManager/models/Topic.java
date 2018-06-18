package com.example.neucourseManager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.event.WindowFocusListener;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name="lesson_id")
    @JsonIgnore
    private Lesson lesson;


    @JsonIgnore
    @OneToMany(mappedBy="topic", orphanRemoval = true)
    private List<Widget> widgets;



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

    public Lesson getLesson() {
        return this.lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }


    public void setWidgets(List<Lesson> lessons) {
        this.widgets = widgets;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

}
