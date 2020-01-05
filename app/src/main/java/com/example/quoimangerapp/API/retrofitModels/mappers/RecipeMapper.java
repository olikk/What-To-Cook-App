package com.example.quoimangerapp.API.retrofitModels.mappers;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.List;

public class RecipeMapper {

    @SerializedName("author")
    private String author;

    @SerializedName("image")
    private File image;

    @SerializedName("ingredients")
    private List<String> ingredients;

    @SerializedName("instructions")
    private List<String> instructions;


    @SerializedName("readyInMinutes")
    private int readyInMinutes;


    @SerializedName("source")
    private String source;

    @SerializedName("title")
    private String title;

    public RecipeMapper() {}

    public RecipeMapper(String author, File image, List<String> ingredients,
                        List<String> instructions, int readyInMinutes,
                        String source, String title) {

        this.author = author;
        this.image = image;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.readyInMinutes = readyInMinutes;
        this.source = source;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }


    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredientsAsStringPerLine() {
        StringBuilder ingredientsPerLineBuilder = new StringBuilder();
        try {
            synchronized (ingredients) {
                final int ingredientsListSize = ingredients.size();
                int i;
                for (i = 0; i < ingredientsListSize - 1; i++) {
                    ingredientsPerLineBuilder.append(ingredients.get(i));
                    ingredientsPerLineBuilder.append(System.getProperty("line.separator"));
                }
                ingredientsPerLineBuilder.append(ingredients.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientsPerLineBuilder.toString();
    }

    public String getInstructionsAsStringPerLine() {
        StringBuilder instructionsPerLineBuilder = new StringBuilder();
        try {
            synchronized (instructions) {
                final int instructionsListSize = instructions.size();
                int i;
                for (i = 0; i < instructionsListSize - 1; i++) {
                    instructionsPerLineBuilder.append(instructions.get(i));
                    instructionsPerLineBuilder.append(System.getProperty("line.separator"));
                }
                instructionsPerLineBuilder.append(instructions.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instructionsPerLineBuilder.toString();
    }

    @Override
    public String toString() {
        return "RecipeMapper{" +
                "author='" + author + '\'' +
                ", image=" + image +
                ", ingredients=" + ingredients +
                ", instructions=" + instructions +
                ", readyInMinutes=" + readyInMinutes +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
