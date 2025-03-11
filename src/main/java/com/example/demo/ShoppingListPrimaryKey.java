package com.example.demo;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;


public class ShoppingListPrimaryKey implements Serializable {
    @Column(name = "id_user")
    private int user;
    @Column(name = "id_ingredient")
    private int ingredient;

    // Конструктор без аргументов
    public ShoppingListPrimaryKey() {}

    public ShoppingListPrimaryKey(int idUser, int idIngredient) {
        this.user = idUser;
        this.ingredient = idIngredient;
    }

    // Геттеры и сеттеры
    public int getIdUser() {
        return user;
    }

    public void setIdUser(int idUser) {
        this.user = idUser;
    }

    public int getIdIngredient() {
        return ingredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.ingredient = idIngredient;
    }

    // Переопределенные equals() и hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListPrimaryKey that = (ShoppingListPrimaryKey) o;
        return user == that.user && ingredient == that.ingredient;
    }

    @Override
    public int hashCode() {
        return 31 * user + ingredient;
    }
}