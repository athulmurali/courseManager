package com.example.neucourseManager.models;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EssayExamQuestion extends BaseQuestion{

    private String essayAnswer;

    public String getEssayAnswer() {
        return essayAnswer;
    }

    public void setEssayAnswer(String essayAnswer) {
        this.essayAnswer = essayAnswer;
    }
}
