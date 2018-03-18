package org.meli.exercise.Meli.mutant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class Dna {
    @JsonProperty("dna")
    private String[] dnaLines;

    public String[] getLines(){
        return dnaLines;
    }

    public void setDnaLines(String[] dnaLines) {
        this.dnaLines = dnaLines;
    }
}
