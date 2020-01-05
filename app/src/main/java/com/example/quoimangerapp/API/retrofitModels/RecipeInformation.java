package com.example.quoimangerapp.API.retrofitModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeInformation {


    @SerializedName("sourceUrl")
    private String sourceUrl = "http://www.epicurious.com/recipes/food/views/Char-Grilled-Beef-Tenderloin-with-Three-Herb-Chimichurri-235342";


    @SerializedName("extendedIngredients")
    private List<ExtendedIngredient> extendedIngredients;

    @SerializedName("id")
    private int id = 0;

    @SerializedName("title")
    private String title = "Char-Grilled Beef Tenderloin with Three-Herb Chimichurri";

    @SerializedName("readyInMinutes")
    private int readyInMinutes = 45;

    @SerializedName("image")
    private String image = "https://spoonacular.com/recipeImages/char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992.jpg";

    @SerializedName("imageType")
    private String imageType = "jpg";

    @SerializedName("instructions")
    private String instructions = "PreparationFor spice rub:";

    public RecipeInformation() {}

    public RecipeInformation(String sourceUrl,
                             List<ExtendedIngredient> extendedIngredients, int id, String title,
                             int readyInMinutes, String image, String imageType, String instructions) {


        this.sourceUrl = sourceUrl;
        this.extendedIngredients = extendedIngredients;
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.image = image;
        this.imageType = imageType;
        this.instructions = instructions;
    }



    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "RecipeInformation{" +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", extendedIngredients=" + extendedIngredients +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", readyInMinutes=" + readyInMinutes +
                ", image='" + image + '\'' +
                ", imageType='" + imageType + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
