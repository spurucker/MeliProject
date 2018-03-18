package org.meli.exercise.Meli.transformers;

public enum StringArrayTransformer {
    TRANSFORMER;

    public String stringFromStringArray(String[] stringArray){
        StringBuilder builder= new StringBuilder();
        builder.append(stringArray[0]);
        for(int i=1; i<stringArray.length; i++){
            builder.append(", ").append(stringArray[i]);
        }
        return builder.toString();
    }
}
