package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_list")
@IdClass(ShoppingListPrimaryKey.class) // Указываем класс, который будет использоваться для составного ключа

public class ShoppingList {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingredient", referencedColumnName = "id_ingredient", insertable = false, updatable = false)
    private Ingredient ingredient;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @Column(name = "status")
    private Boolean status;

    // Геттеры и сеттеры


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
