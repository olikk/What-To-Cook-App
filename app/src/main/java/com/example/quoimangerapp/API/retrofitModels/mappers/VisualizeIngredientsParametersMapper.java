package com.example.quoimangerapp.API.retrofitModels.mappers;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VisualizeIngredientsParametersMapper {


    @SerializedName("ingredientList")
    private List<String> ingredientList = new ArrayList<>();

    public VisualizeIngredientsParametersMapper() {}

    public VisualizeIngredientsParametersMapper(List<String> ingredientList) {

        this.ingredientList = ingredientList;
    }



    public List<String> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getIngredientsListAsStringPerLine() {

        StringBuilder ingredientsBuilder = new StringBuilder();
        try {
            synchronized (ingredientList) {
                int ingredientsListSize = ingredientList.size();
                int i;
                for(i = 0; i < ingredientsListSize - 1; i++) {
                    ingredientsBuilder.append(ingredientList.get(i));
                    ingredientsBuilder.append(System.getProperty("line.separator"));
                }
                ingredientsBuilder.append(ingredientList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientsBuilder.toString();
    }



    @Override
    public String toString() {
        return "VisualizeIngredientsParametersMapper{" +
                ", ingredientList=" + ingredientList +
                '}';
    }
}
