package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "favourite_recipe")
@IdClass(FavouriteRecipePrimaryKey.class)  // Указываем класс составного ключа
public class FavouriteRecipe{

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private User user;  // Сущность User, связывающаяся с таблицей users

    @Id
    @ManyToOne
    @JoinColumn(name = "id_recipe", referencedColumnName = "id_recipe", insertable = false, updatable = false)
    private Recipe recipe;  // Сущность Recipe, связывающаяся с таблицей recipes

    // Конструкторы, геттеры и сеттеры

    public FavouriteRecipe() {
    }

    public FavouriteRecipe(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}