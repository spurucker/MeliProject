package org.meli.exercise.Meli.validators;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.meli.exercise.Meli.exceptions.InvalidDnaException;
import org.meli.exercise.Meli.mutant.Dna;

public class ServiceValidatorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private final ServiceValidator validator= new ServiceValidator();

    @Test
    public void nullDna(){
        thrown.expect(InvalidDnaException.class);
        thrown.expectMessage("No DNA was given");
        validator.isDnaValid(null);
    }

    @Test
    public void emptyDna(){
        thrown.expect(InvalidDnaException.class);
        thrown.expectMessage("No DNA was given");
        validator.isDnaValid(new Dna());
    }

    @Test
    public void tooShortDna(){
        thrown.expect(InvalidDnaException.class);
        thrown.expectMessage("DNA structure is too short");
        String[] dnaTable= new String[]{"AAA", "AAA", "AAA"};
        Dna dna= new Dna();
        dna.setDnaLines(dnaTable);
        validator.isDnaValid(dna);
    }

    @Test
    public void notNxNDna(){
        thrown.expect(InvalidDnaException.class);
        thrown.expectMessage("DNA structure is no NxN table");
        String[] dnaTable= new String[]{"AAAA", "AAAA", "AAAA","AAAAA"};
        Dna dna= new Dna();
        dna.setDnaLines(dnaTable);
        validator.isDnaValid(dna);
    }

    @Test
    public void invalidValuesDna(){
        thrown.expect(InvalidDnaException.class);
        thrown.expectMessage("Only A, C, T or G are valid character in an DNA table");
        String[] dnaTable= new String[]{"AAAA", "AAAA", "AAAA","AAAZ"};
        Dna dna= new Dna();
        dna.setDnaLines(dnaTable);
        validator.isDnaValid(dna);
    }

    @Test
    public void validDna(){
        String[] dnaTable= new String[]{"AAAA", "AAAA", "AAAA","AAAA"};
        Dna dna= new Dna();
        dna.setDnaLines(dnaTable);
        validator.isDnaValid(dna);
    }
}
