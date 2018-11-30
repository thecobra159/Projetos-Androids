package com.example.thecobra.colors;

import java.util.ArrayList;

public class Color {
    private String color, category, type;
    private ArrayList<String> code;

    public Color() { }

    public Color(String color, String category, String type, ArrayList<String> code) {
        this.color = color;
        this.category = category;
        this.type = type;
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public void setCode(ArrayList<String> code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Color: " + getColor() + " Category: " + getCategory() + " Type: " + getType() + " Code: " + getCode().toString();
    }
}
