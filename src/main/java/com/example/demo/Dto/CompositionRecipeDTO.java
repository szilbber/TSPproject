package com.example.demo.Dto;

public class CompositionRecipeDTO {
    private Integer ingredientId; // ID ингредиента
    private double quantity; // Количество ингредиента

    // Геттеры и сеттеры
    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}