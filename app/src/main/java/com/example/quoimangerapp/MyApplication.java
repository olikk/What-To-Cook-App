package com.example.quoimangerapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quoimangerapp.Database.AppDatabase;
import com.facebook.stetho.Stetho;

public class MyApplication extends Application {
    public static MyApplication instance;

    private AppDatabase database;
    public void onCreate(){
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "quoimangerapp-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void destroyInstance() {
        instance = null;
    }
}