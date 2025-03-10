package com.example.demo;
import java.io.Serializable;
import java.util.Objects;

public class CompositionPrimaryKey implements Serializable {
    private int idRecipe;
    private int idIngredient;

    public CompositionPrimaryKey() {
    }

    public CompositionPrimaryKey(int idRecipe, int idIngredient) {
        this.idRecipe = idRecipe;
        this.idIngredient = idIngredient;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionPrimaryKey that = (CompositionPrimaryKey) o;
        return idRecipe == that.idRecipe && idIngredient == that.idIngredient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecipe, idIngredient);
    }
}
