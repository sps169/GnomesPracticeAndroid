package com.example.gnomespractice;

import androidx.room.TypeConverter;

import com.example.gnomespractice.model.Color;

public class Utils {
    @TypeConverter
    public Color fromString(String value) {
        return Color.valueOf(value);
    }

    @TypeConverter
    public String fromColor(Color color) {
        return color.name();
    }
}
