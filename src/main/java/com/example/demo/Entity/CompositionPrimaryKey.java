package com.example.demo.Entity;
import java.io.Serializable;
import java.util.Objects;

public class CompositionPrimaryKey implements Serializable {

    private Integer recipe;
    private Integer ingredient;

    // Конструктор по умолчанию
    public CompositionPrimaryKey() {}

    // Конструктор с параметрами
    public CompositionPrimaryKey(Integer recipeId, Integer ingredientId) {
        this.recipe = recipeId;
        this.ingredient = ingredientId;
    }

    // equals() и hashCode() для составного ключа
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionPrimaryKey that = (CompositionPrimaryKey) o;
        return Objects.equals(recipe, that.recipe) &&
                Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingredient);
    }
}