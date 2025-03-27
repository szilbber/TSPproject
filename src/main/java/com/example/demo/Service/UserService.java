package com.example.demo.Service;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordService passwordUtil) {
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
    }

    @Autowired
    private EntityManager entityManager;
    @Transactional
    // Регистрация пользователя и обновление
    public User registerUser(User user) {
        // Хешируем пароль перед сохранением
        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // Проверка пароля пользователя, то есть вход
    public boolean checkPassword(String rawPassword, String email) {
        User user = userRepository.findByMail(email);
        return user != null && passwordUtil.matches(rawPassword, user.getPassword());
    }

    // Метод для поиска пользователя по email
    public User getUserByEmail(String email) {
        return userRepository.findByMail(email);
    }

    // Метод для поиска пользователя по имени
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Метод для поиска пользователя по ID
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Метод для удаления пользователя по его ID
    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }
    public User updateUser(User updatedUser) {

        User existingUser = getUserById(updatedUser.getId_user());
        // При изменении пароля – хэшируем заново
        if (!existingUser.getPassword().equals(updatedUser.getPassword())) {
            existingUser.setPassword(passwordUtil.encodePassword(updatedUser.getPassword()));
        }

        existingUser.setBday(updatedUser.getBday());
        existingUser.setMail(updatedUser.getMail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setName(updatedUser.getName());

        return userRepository.save(existingUser);
    }

}