package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")  // Название внешнего ключа в таблице
    private User userId;  // Поле, которое ссылается на сущность User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")  // Название внешнего ключа в таблице
    private Category category;  // Поле, которое ссылается на сущность


    @ManyToMany(mappedBy = "recipes") //список пользователей, которые добавили рецепт в избранное
    private Set<User> fav_users = new HashSet<>();

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompositionRecipe> ingredients;


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "manual")
    private String manual;

    @Column(name = "time")
    private String time;

    @Column(name = "picture")
    private byte[] picture;

    // Конструкторы
    public Recipe() {}

    public Recipe(User userId, Category category, String title, String description, String manual, String time, byte[] picture) {
        this.userId = userId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.manual = manual;
        this.time = time;
        this.picture = picture;
    }


    // Геттеры и сеттеры
    public Set<CompositionRecipe> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<CompositionRecipe> ingredients) {
        this.ingredients = ingredients;
    }

    public int getId_recipe() {
        return id;
    }

    public void setId_recipe(int id_recipe) {
        this.id = id_recipe;
    }

    public User getUser() {
        return userId;
    }

    public void setUser(User user) {
        this.userId = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
