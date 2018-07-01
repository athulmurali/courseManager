package com.example.neucourseManager.models.exam;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MultipleChoiceExamQuestion extends  Question{

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
