package com.example.demo.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationRequestDto {

    private String username;
    private String password;
    private String phone;
    private String email;
    private LocalDate bday;

    // Конструктор без параметров
    public RegistrationRequestDto() {
    }

    // Конструктор с параметрами
    public RegistrationRequestDto(String username, String email, String password, LocalDate bday, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.bday = bday;
        this.phone = phone;
    }

    // Геттер для username
    public String getUsername() {
        return username;
    }

    // Сеттер для username
    public void setUsername(String username) {
        this.username = username;
    }

    // Геттер для email
    public String getEmail() {
        return email;
    }

    // Сеттер для email
    public void setEmail(String email) {
        this.email = email;
    }

    // Геттер для password
    public String getPassword() {
        return password;
    }

    // Сеттер для password
    public void setPassword(String password) {
        this.password = password;
    }

    // Геттер для password
    public String getPhone() {
        return phone;
    }

    // Сеттер для password
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Геттер для password
    public LocalDate getBday() {
        return bday;
    }

    // Сеттер для password
    public void setBday(LocalDate bday) {
        this.bday = bday;
    }
}
