package com.example.neucourseManager.models.exam;


import javax.persistence.Entity;

@Entity
public class EssayExamQuestion extends BaseExamQuestion {

    private String essayAnswer;

    public String getEssayAnswer() {
        return essayAnswer;
    }

    public void setEssayAnswer(String essayAnswer) {
        this.essayAnswer = essayAnswer;
    }
}
