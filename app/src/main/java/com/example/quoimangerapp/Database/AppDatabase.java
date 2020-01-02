package com.example.quoimangerapp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.quoimangerapp.Database.DAO.RecipeDao;
import com.example.quoimangerapp.Database.DAO.UserDao;
import com.example.quoimangerapp.Database.Entity.Recipe;
import com.example.quoimangerapp.Database.Entity.User;

@Database(entities = {User.class, Recipe.class}, version = 1)
public abstract class  AppDatabase extends RoomDatabase {

    private static AppDatabase Instance;

    public abstract UserDao userDao();
    public abstract RecipeDao recipeDao();

    /*static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
*/
    public static void destroyInstance() {
        Instance = null;
    }

}
