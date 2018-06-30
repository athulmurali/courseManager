package com.example.neucourseManager.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MCQ_QUESTION")
public class MultipleChoiceExamQuestion extends  BaseQuestion{

    @ElementCollection
    private List<String> options = new ArrayList<>();
        private int correctOptionIndex;
        public List<String> getOptions() {
            return options;
        }
        public void setOptions(List<String> options) {
            this.options = options;
        }
        public int getCorrectOptionIndex() {
            return correctOptionIndex;
        }
        public void setCorrectOption(int correctOptionIndex) {
            this.correctOptionIndex = correctOptionIndex;
        }

}
