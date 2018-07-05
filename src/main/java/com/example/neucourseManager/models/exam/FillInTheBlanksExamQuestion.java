package com.example.neucourseManager.models.exam;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity

public class FillInTheBlanksExamQuestion

        extends BaseExamQuestion {


    public String getTextWithBrackets() {
        return textWithBrackets;
    }

    public void setTextWithBrackets(String textWithBrackets) {
        this.textWithBrackets = textWithBrackets;
    }

    public List<String> getVariablesList() {
        return variablesList;
    }

    public void setVariablesList(List<String> variablesList) {
        this.variablesList = variablesList;
    }

    private String textWithBrackets;
    @ElementCollection
    private List<String> variablesList = new ArrayList<>();


}
