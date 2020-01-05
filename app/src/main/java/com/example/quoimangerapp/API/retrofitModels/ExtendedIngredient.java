package com.example.quoimangerapp.API.retrofitModels;

import com.google.gson.annotations.SerializedName;

public class ExtendedIngredient {
    @SerializedName("originalString")
    private String originalString = "1 1/2 teaspoons chipotle chile powder or ancho chile powder";

    public ExtendedIngredient() {}

    public ExtendedIngredient(String originalString){
        this.originalString = originalString;
    }

    public String getOriginalString() {
        return originalString;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }
}
