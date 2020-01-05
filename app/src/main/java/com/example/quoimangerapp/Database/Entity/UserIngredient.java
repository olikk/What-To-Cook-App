package com.example.quoimangerapp.Database.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_ingredients")
public class UserIngredient {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user")
    private int user_id;

    @ColumnInfo(name = "ingredient")
    private String ingredient;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
