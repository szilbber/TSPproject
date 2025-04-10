package com.example.demo.Dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class SignUpRequest {

    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;
    @Pattern (regexp = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$", message = "Email адрес должен быть в правильном формате user@example.com")
    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;

    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;

    @Pattern(regexp = "^\\+?[1-9][0-9]{1,14}$", message = "Номер телефона должен быть в международном формате")
    private String phoneNumber;
}