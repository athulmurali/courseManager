package com.example.neucourseManager.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Widget {


    @Id
    private int id;
    private String text;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
