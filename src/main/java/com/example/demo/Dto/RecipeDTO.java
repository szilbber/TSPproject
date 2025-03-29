package com.example.demo.Dto;

import java.util.List;

public class RecipeDTO {
    private String title;
    private String description;
    private String manual;
    private String time;
    private Integer id_user; // Используем ID пользователя
    private Integer id_category; // Используем ID категории
    private List<IngredientDTO> ingredients;

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return id_user;
    }

    public void setUserId(Integer userId) {
        this.id_user = userId;
    }

    public Integer getCategoryId() {
        return id_category;
    }

    public void setCategoryId(Integer categoryId) {
        this.id_category = categoryId;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
