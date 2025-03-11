package com.example.demo;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "users")
 // указываем имя таблицы в базе данных
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // автоматически генерируем значение для id
    private int id_user;
    private String name;
    private String password;
    private String phone;
    private String mail;
    private LocalDate bday;

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
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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