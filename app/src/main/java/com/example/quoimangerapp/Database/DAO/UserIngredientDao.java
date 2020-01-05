package com.example.quoimangerapp.Database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quoimangerapp.Database.Entity.UserIngredient;

import java.util.List;

@Dao
public interface UserIngredientDao {

    @Query("SELECT * FROM user_ingredients")
    List<UserIngredient> getAll();

    @Query("SELECT * FROM user_ingredients where user LIKE  :user")
    List<UserIngredient> findByUser(int user);

    @Query("SELECT COUNT(*) from user_ingredients where user LIKE  :user")
    int countRecipes(int user);

    @Insert
    void insertAll(UserIngredient... ingredients);

    @Delete
    void delete(UserIngredient ingredient);
}
