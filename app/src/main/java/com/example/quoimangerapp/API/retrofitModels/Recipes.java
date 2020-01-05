package com.example.quoimangerapp.API.retrofitModels;

import com.google.gson.annotations.SerializedName;

public class Recipes {
    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private String image;

    @SerializedName("title")
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", titile=" + title +
                '}';
    }
}
