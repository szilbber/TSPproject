package com.example.demo.Dto;

import lombok.Data;

@Data
public class IngredientDTO {
    private Integer ingredientId;
    private double quantity;
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
