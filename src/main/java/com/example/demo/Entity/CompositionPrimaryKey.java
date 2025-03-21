package com.example.demo.Entity;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class CompositionPrimaryKey implements Serializable {
    @Column(name = "id_ingredient")
    private int ingredient;

    @Column(name = "id_recipe")
    private int recipe;

    public CompositionPrimaryKey() {
    }

    public CompositionPrimaryKey(int idRecipe, int idIngredient) {
        this.recipe = idRecipe;
        this.ingredient = idIngredient;
    }

    public int getIdRecipe() {
        return recipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.recipe = idRecipe;
    }

    public int getIdIngredient() {
        return ingredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.ingredient = idIngredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionPrimaryKey that = (CompositionPrimaryKey) o;
        return recipe == that.recipe && ingredient == that.ingredient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingredient);
    }
}
