package com.example.quoimangerapp.API.retrofitModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipesList {
    @SerializedName("results")
    private List<Recipes> recipesList;

    public List<Recipes> getRecipesList() {
        return recipesList;
    }

    public void setRecipesList(List<Recipes> recipesList) {
        this.recipesList = recipesList;
    }
}
