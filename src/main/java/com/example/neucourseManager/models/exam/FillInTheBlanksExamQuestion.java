package com.example.neucourseManager.models.exam;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class FillInTheBlanksExamQuestion

        extends Question {


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



    @Column(name = "VARIABLES", nullable = false)
    private String variables;



    public String getVariables() {
        return variables;
    }
    public void setVariables(String variables) {
        this.variables = variables;
    }
}
