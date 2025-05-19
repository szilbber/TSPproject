package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)  // Не допускаем NULL, но не каскадируем удаление
    private User userId;  // Поле, которое ссылается на сущность User, автор рецепта


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")  // Название внешнего ключа в таблице
    private Category category;  // Поле, которое ссылается на сущность

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "manual")
    private String manual;

    @Column(name = "time")
    private String time;


    @ManyToMany(mappedBy = "favouriteRecipes")  // Указываем, что связь с User уже установлена в сущности User
    private Set<User> usersWhoFavourited = new HashSet<>();  // Множество пользователей, которые добавили этот рецепт в избранное

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompositionRecipe> ingredients=new HashSet<>();


//    @Column(name = "picture")
//    private byte[] picture;

    // Конструкторы
    public Recipe() {}

    // @JsonCreator
    public Recipe(User userId, Category category, String title, String description, String manual, String time//, byte[] picture
    ) {
        this.userId = userId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.manual = manual;
        this.time = time;
//        this.picture = picture;
    }


    // Геттеры и сеттеры
    public Set<CompositionRecipe> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<CompositionRecipe> ingredients) {
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_recipe) {
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

//    public byte[] getPicture() {
//        return picture;
//    }
//
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }
}