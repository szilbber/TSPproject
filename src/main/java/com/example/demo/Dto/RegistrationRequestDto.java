package com.example.demo.Dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationRequestDto {

    private String username;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву, одну цифру и один специальный символ. Длина пароля должна быть не менее 8 символов."
    )
    private String password;
    @Pattern(
            regexp = "^7\\d{10}$",
            message = "Номер телефона должен быть в правильном формате (например, +79991234567)"
    ) private String phone;
    @Pattern(
            regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Email адрес должен быть в правильном формате (например, user@example.com)"
    )
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
