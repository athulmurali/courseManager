package com.example.neucourseManager.models.exam;


import com.example.neucourseManager.models.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column(nullable = false)
    private String title;
    private String description;



    @ManyToOne
    @JoinColumn(name="topic_id")
    @JsonIgnore
    private Topic topic;

    @OneToMany(mappedBy="exam", orphanRemoval = true)
    @JsonIgnore
    private List<BaseExamQuestion> questions;



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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<BaseExamQuestion> getQuestions() {
        return questions;
    }
    public void setQuestions(List<BaseExamQuestion> questions) {
        this.questions = questions;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
