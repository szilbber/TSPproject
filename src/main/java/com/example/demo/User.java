package com.example.demo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")  // указываем имя таблицы в базе данных
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // автоматически генерируем значение для id
    @Column(name = "id_user")  // указываем имя столбца
    private int id_user;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "bday")
    private LocalDate bday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

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