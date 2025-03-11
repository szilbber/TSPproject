package com.example.demo;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class FavouriteRecipePrimaryKey implements Serializable {
    @Column(name = "id_user")
    private int user;
    @Column(name = "id_recipe")
    private int recipe;

    // Конструкторы, геттеры и сеттеры

    public FavouriteRecipePrimaryKey() {
    }

    public FavouriteRecipePrimaryKey(int idUser, int idRecipe) {
        this.user = idUser;
        this.recipe = idRecipe;
    }

    public int getIdUser() {
        return user;
    }

    public void setIdUser(int idUser) {
        this.user = idUser;
    }

    public int getIdRecipe() {
        return recipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.recipe = idRecipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteRecipePrimaryKey that = (FavouriteRecipePrimaryKey) o;
        return user == that.user && recipe == that.recipe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, recipe);
    }
}
