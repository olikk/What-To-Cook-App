package com.example.quoimangerapp.API.retrofitModels.mappers;

import com.google.gson.annotations.SerializedName;

public class RecipeSummaryMapper {
    @SerializedName("id")
    private int id;

    public RecipeSummaryMapper() {}

    public RecipeSummaryMapper(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeSummaryMapper{" +
                "id=" + id +
                '}';
    }
}
