package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordUtil;

    // Регистрация пользователя
    public User registerUser(User user) {
        // Хешируем пароль перед сохранением
        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // Проверка пароля пользователя
    public boolean checkPassword(String rawPassword, String email) {
        User user = userRepository.findByMail(email);
        return passwordUtil.matches(rawPassword, user.getPassword());
    }
}