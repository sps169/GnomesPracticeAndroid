package com.example.gnomespractice;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gnomespractice.model.Gnome;

import java.util.List;

@Dao
public interface DaoGnome {
    @Query("SELECT * FROM gnome")
    List<Gnome> getAll();

    @Query("SELECT * FROM gnome WHERE name LIKE :name")
    Gnome findByName(String name);

    @Insert
    void insertAll(Gnome... gnome);

    @Insert
    void insert(Gnome gnome);

    @Update
    void update(Gnome gnome);

    @Delete
    void delete(Gnome gnome);
}
