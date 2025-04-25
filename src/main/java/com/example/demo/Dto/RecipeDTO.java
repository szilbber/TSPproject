package com.example.demo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {

    private Integer categoryId; // Используем ID категории
    private String title;
    private String description;
    private String manual;
    private String time;
    private List<IngredientDTO> ingredients;

    public RecipeDTO
            (Integer categoryId, String title, String description, String manual, String time, List<IngredientDTO> ingredientTitles)
    {
        this.categoryId =categoryId;
        this.title = title;
        this.description = description;
        this.manual = manual;
        this.time = time;
        this.ingredients=ingredientTitles;
    }


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
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public Integer  getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
