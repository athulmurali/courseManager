package com.example.neucourseManager.models.exam;

import javax.persistence.Entity;


@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion {
    private boolean answer;

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }



}
