package com.example.demo.Dto;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class UserProfileDTO {
    private String username;
    private String phone;
    private String email;
    private LocalDate bday;

    // Конструктор без параметров
    public UserProfileDTO() {
    }

    // Конструктор с параметрами
    public UserProfileDTO(String username, String email, String password, LocalDate bday, String phone) {
        this.username = username;
        this.email = email;
        this.bday = bday;
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public LocalDate getBday() {
        return bday;
    }


    public void setBday(LocalDate bday) {
        this.bday = bday;
    }
}
