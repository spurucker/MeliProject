package org.meli.exercise.Meli.mutant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Stat {
    private int countMutant;
    private int countHuman;
    private float ratio;

    public Stat(int countHuman, int countMutant){
        this.countHuman= countHuman;
        this.countMutant= countMutant;
        setRadio();
    }

    public int getCountMutant() {
        return countMutant;
    }

    public int getCountHuman() {
        return countHuman;
    }

    public float getRatio() {
        return ratio;
    }

    private void setRadio(){
        if(countMutant>0 && countHuman>0) {
            ratio =(float)Math.round((float) countMutant / (countHuman + countMutant)*100)/100;
        }else if(countMutant==0){
            ratio= 0;
        }else {
            ratio= 1;
        }
    }
}
