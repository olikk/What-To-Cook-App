package com.example.quoimangerapp.API.retrofitModels;

import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private String image;

    @SerializedName("usedIngredientCount")
    private Integer usedIngredientCount;

    @SerializedName("missedIngredientCount")
    private Integer missedIngredientCount;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", usedIngredientCount=" + usedIngredientCount +
                ", missedIngredientCount=" + missedIngredientCount +
                ", likes=" +
                '}';
    }
}
