package com.example.demo;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class FavouriteRecipePrimaryKey implements Serializable {
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_recipe")
    private int idRecipe;

    // Конструкторы, геттеры и сеттеры

    public FavouriteRecipePrimaryKey() {
    }

    public FavouriteRecipePrimaryKey(int idUser, int idRecipe) {
        this.idUser = idUser;
        this.idRecipe = idRecipe;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteRecipePrimaryKey that = (FavouriteRecipePrimaryKey) o;
        return idUser == that.idUser && idRecipe == that.idRecipe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRecipe);
    }
}
