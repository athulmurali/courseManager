package com.example.neucourseManager.models.exam;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "BASE_QUESTION")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class BaseQuestion extends Exam {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private int points;
    private String title;
    private String description;
    private String instructions;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
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
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }



}
