package com.example.neucourseManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PER_CLASS_TRUE_OR_FALSE_QUESTION")
public class TrueOrFalseExamQuestion
        extends BaseQuestion {
    @Column(name = "I   S_TRUE", nullable = false)
    private Boolean isTrue;
    public Boolean getIsTrue() {
        return isTrue;
    }
    public void setIsTrue(Boolean isTrue) {
        this.isTrue = isTrue;
    }
}
