package com.example.gnomespractice.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class Gnome implements Serializable {
    @PrimaryKey
    @NotNull
    private String name;
    @NotNull
    private Color color;
    private String caramelo_id;

    public Gnome(@NonNull String name, @NonNull Color color, String caramelo_id) {
        this.name = name;
        this.color = color;
        this.caramelo_id = caramelo_id;
    }

    public Gnome() {
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(@NonNull Color color) {
        this.color = color;
    }

    public String getCaramelo_id() {
        return caramelo_id;
    }

    public void setCaramelo_id(String caramelo_id) {
        this.caramelo_id = caramelo_id;
    }
}
