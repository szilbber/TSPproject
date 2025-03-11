package com.example.demo;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;


public class ShoppingListPrimaryKey implements Serializable {
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_ingredient")
    private int idIngredient;

    // Конструктор без аргументов
    public ShoppingListPrimaryKey() {}

    public ShoppingListPrimaryKey(int idUser, int idIngredient) {
        this.idUser = idUser;
        this.idIngredient = idIngredient;
    }

    // Геттеры и сеттеры
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    // Переопределенные equals() и hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListPrimaryKey that = (ShoppingListPrimaryKey) o;
        return idUser == that.idUser && idIngredient == that.idIngredient;
    }

    @Override
    public int hashCode() {
        return 31 * idUser + idIngredient;
    }
}