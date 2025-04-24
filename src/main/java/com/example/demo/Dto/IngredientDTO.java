package com.example.demo.Dto;

import lombok.Data;

@Data
public class IngredientDTO {
    private Integer ingredientId;
    private String title;
    private String unit;
    private double quantity;

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getTitle() {
        return title;
    }

    public void setIngredientTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setIngredientUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
