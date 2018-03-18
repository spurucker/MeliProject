package org.meli.exercise.Meli.validators;

import org.meli.exercise.Meli.mutant.Dna;
import org.meli.exercise.Meli.exceptions.InvalidDnaException;
import org.springframework.stereotype.Component;

@Component
public class ServiceValidator {
    public void isDnaValid(Dna dna) throws InvalidDnaException {
        checkDnaIsNotNull(dna);
        checkDnaLength(dna.getLines());
        checkNxNTable(dna.getLines());
        checkValidChars(dna.getLines());
    }

    private void checkDnaIsNotNull(Dna dna){
        if(dna == null || dna.getLines() == null){
            throw new InvalidDnaException("No DNA was given");
        }
    }

    private void checkDnaLength(String[] dna){
        if(dna.length<4) {
            throw new InvalidDnaException("DNA structure is too short");
        }
    }

    private void checkNxNTable(String[] dna){
        if(!stringArrayLengthAreEquals(dna) || dna.length != dna[0].length()) {
            throw new InvalidDnaException("DNA structure is no NxN table");
        }
    }

    private void checkValidChars(String[] dna){
        int i=0;
        boolean validChars=true;
        while(i<dna.length && validChars){
            if(!(dna[i].matches("[ACTG]*"))){
                validChars=false;
            }
            i++;
        }
        if (!validChars){
            throw new InvalidDnaException("Only A, C, T or G are valid character in an DNA table");
        }
    }

    private boolean stringArrayLengthAreEquals(String[] stringArray){
        int i=1;
        boolean areEquals= true;
        int actualSize= stringArray[0].length();
        while (areEquals && i<stringArray.length){
            areEquals= actualSize == stringArray[i].length();
            actualSize= stringArray[i].length();
            i++;
        }
        return areEquals;
    }
}
