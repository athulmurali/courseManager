package com.example.neucourseManager.models.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
public class TrueOrFalseExamQuestion extends Question {
    private boolean answer;

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }



}
