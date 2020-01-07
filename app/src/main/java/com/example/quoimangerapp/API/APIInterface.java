package com.example.quoimangerapp.API;

import com.example.quoimangerapp.API.retrofitModels.Recipe;
import com.example.quoimangerapp.API.retrofitModels.RecipeInformation;
import com.example.quoimangerapp.API.retrofitModels.RecipeSummary;
import com.example.quoimangerapp.API.retrofitModels.Recipes;
import com.example.quoimangerapp.API.retrofitModels.RecipesList;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface  {


    @GET("recipes/search")
    Call<RecipesList> allRecipes(
            @Header("Content-Type") String contentType,
            @Header("Accept") String accept,
            @Query("apiKey") String apiKey,
            @Query("number") int number
    );

    @GET("recipes/{id}/summary")
    Call<RecipeSummary> summarizeRecipe(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );


    @POST("recipes/visualizeIngredients")
    Call<String> visualizeIngredients(
            @Field("ingredientList") String ingredientsList,
            @Query("apiKey") String apiKey
    );


    @GET("recipes/findByIngredients")
    Call<List<Recipes>> findRecipesByIngredients(
            @Header("Content-Type") String contentType,
            @Header("Accept") String accept,
            @Query("ingredients") String ingredients,
            @Query("limitLicense") boolean limitLicense,
            @Query("number") Integer number,
            @Query("ranking") Integer ranking,
            @Query("apiKey") String apiKey
    );

    @GET("recipes/{id}/information")
    Call<RecipeInformation> getRecipeInformation(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );


}