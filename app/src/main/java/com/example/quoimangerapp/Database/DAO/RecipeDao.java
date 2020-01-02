package com.example.quoimangerapp.Database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quoimangerapp.Database.Entity.Recipe;
import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("SELECT * FROM recipe where title LIKE  :title")
    Recipe findByTitle(String title);

    @Query("SELECT COUNT(*) from recipe")
    int countRecipes();

    @Insert
    void insertAll(Recipe... recipes);

    @Delete
    void delete(Recipe recipe);
}
