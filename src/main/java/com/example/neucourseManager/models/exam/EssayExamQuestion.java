package com.example.neucourseManager.models.exam;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ESSAY_QUESTION")
public class EssayExamQuestion extends BaseQuestion{

    private String essayAnswer;

    public String getEssayAnswer() {
        return essayAnswer;
    }

    public void setEssayAnswer(String essayAnswer) {
        this.essayAnswer = essayAnswer;
    }
}
