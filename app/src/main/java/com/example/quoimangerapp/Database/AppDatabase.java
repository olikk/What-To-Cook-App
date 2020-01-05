package com.example.quoimangerapp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.quoimangerapp.Database.DAO.UserDao;
import com.example.quoimangerapp.Database.DAO.UserIngredientDao;
import com.example.quoimangerapp.Database.Entity.User;
import com.example.quoimangerapp.Database.Entity.UserIngredient;

@Database(entities = {User.class, UserIngredient.class}, version = 2)
public abstract class  AppDatabase extends RoomDatabase {

    private static AppDatabase Instance;

    public abstract UserDao userDao();
    public abstract UserIngredientDao userIngredientDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

    public static void destroyInstance() {
        Instance = null;
    }

}
