package com.example.gnomespractice.model;

public class Gnome {
    private String name;
    private Color color;
    private String caramelo_id;

    public Gnome(String name, Color color, String caramelo_id) {
        this.name = name;
        this.color = color;
        this.caramelo_id = caramelo_id;
    }

    public Gnome() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getCaramelo_id() {
        return caramelo_id;
    }

    public void setCaramelo_id(String caramelo_id) {
        this.caramelo_id = caramelo_id;
    }
}
