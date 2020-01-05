package com.example.quoimangerapp.API.retrofitModels.mappers;

import com.google.gson.annotations.SerializedName;

public class RecipeInformationMapper {
    @SerializedName("id")
    private int id;

    @SerializedName("includeNutrition")
    private boolean includeNutrition;

    public RecipeInformationMapper() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeInformationMapper{" +
                "id=" + id +
                '}';
    }
}
