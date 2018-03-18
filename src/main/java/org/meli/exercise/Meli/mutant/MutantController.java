package org.meli.exercise.Meli.mutant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MutantController {
    @Autowired
    private MutantDAO dao;

    public boolean isMutant(String[] dna){
        boolean isMutant= false;
        int i=0;
        int j=0;
        while(i<dna.length && !isMutant){
            while(j<dna[i].length() && !isMutant) {
                isMutant = isThisCharEvidenceOfMutation(dna, i, j);
                j++;
            }
            j=0;
            i++;
        }
        dao.addDna(dna, isMutant);
        return isMutant;
    }

    public Stat getStats(){
        return dao.getStats();
    }

    private boolean isThisCharEvidenceOfMutation(String[] dna, int i, int j){
        return isOnUpperTDiagonal(dna, i, j) || isOnLowerDiagonal(dna, i, j)
                || isOnTheHorizontal(dna, i, j) || isOnTheVertical(dna, i, j);
    }

    private boolean isOnTheHorizontal(String dna[], int i, int j){
        if(dna[i].length()-j<4){
            return false;
        }
        if(dna[i].charAt(j)==dna[i].charAt(j+1) && dna[i].charAt(j)==dna[i].charAt(j+2)
                && dna[i].charAt(j)==dna[i].charAt(j+3)){
            return true;
        }
        return false;
    }

    private boolean isOnTheVertical(String dna[], int i, int j){
        if(dna.length-i<4){
            return false;
        }
        if(dna[i].charAt(j)==dna[i+1].charAt(j) && dna[i+2].charAt(j)==dna[i].charAt(j)
                && dna[i].charAt(j)==dna[i+3].charAt(j)){
            return true;
        }
        return false;
    }

    private boolean isOnUpperTDiagonal(String dna[], int i, int j){
        if(i<3 || dna[i].length()-j<3){
            return false;
        }
        if(dna[i].charAt(j)==dna[i-1].charAt(j+1) && dna[i].charAt(j)==dna[i-2].charAt(j+2)
                && dna[i].charAt(j)==dna[i-3].charAt(j+3)){
            return true;
        }
        return false;
    }

    private boolean isOnLowerDiagonal(String dna[], int i, int j){
        if(dna.length-i<3 || dna[i].length()-j<3){
            return false;
        }
        if(dna[i].charAt(j)==dna[i+1].charAt(j+1) && dna[i].charAt(j)==dna[i+2].charAt(j+2)
                && dna[i].charAt(j)==dna[i+3].charAt(j+3)){
            return true;
        }
        return false;
    }
}
