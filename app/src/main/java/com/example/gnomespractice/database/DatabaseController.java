package com.example.gnomespractice.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.gnomespractice.DaoGnome;
import com.example.gnomespractice.Utils;
import com.example.gnomespractice.model.Gnome;

@Database(entities = {Gnome.class}, version = 2)
@TypeConverters(Utils.class)
public abstract class DatabaseController extends RoomDatabase {

    private static volatile DatabaseController instance;

    public abstract DaoGnome gnomeDao();

    public synchronized static DatabaseController getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseController.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
