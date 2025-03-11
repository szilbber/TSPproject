package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    private int id_recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")  // Название внешнего ключа в таблице
    private User user;  // Поле, которое ссылается на сущность User

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

    @Column(name = "picture")
    private byte[] picture;

    // Геттеры и сеттеры
    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
