package com.example.demo.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
 // указываем имя таблицы в базе данных
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // автоматически генерируем значение для id
    private Integer id;
    private String name;
    private String password;
    private String phone;
    private String mail;
    private LocalDate bday;

    @ManyToMany
    @JoinTable(
            name = "favourite_recipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<Recipe> fav_recipes = new HashSet<>();

    // Конструкторы, геттеры и сеттеры
    public User() {}

    public User(String mail, String password, String name, String phone, LocalDate bday) {

        this.mail = mail;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.bday = bday;
    }

    // Геттеры и сеттеры
    public Integer getId_user() {
        return id;
    }

    public void setId_user(Integer id_user) {
        this.id= id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}