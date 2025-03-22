package com.example.demo.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Recipe;
import com.example.demo.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeServiceTest {
//    @Autowired
//    private RecipeService recipeService;
//
//    User userId=1;
//    Category category;
//    String title;
//    String description;
//    String manual;
//    String time;
//    byte[] picture;
//
//    Recipe recipe = new Recipe();
//
//    @Test
//    public void testRegisterUser() {
//        assertNotNull(userService.registerUser(user).getId_user());
//        assertNotNull(userService.getUserByEmail("mail@example.com"));
//        assertTrue(userService.checkPassword("password", "mail@example.com"));
//        assertEquals(userService.getUserByName("name").get().getId_user(), user.getId_user());
//        assertEquals(userService.getUserById(user.getId_user()).get().getId_user(), user.getId_user());
//    }
}

// Метод для получения рецепта по его ID

// Метод для получения всех рецептов пользователя по его ID

// Метод для получения всех рецептов по категории


// Метод для поиска рецептов по части названия (с использованием LIKE)


// Метод для создания или обновления рецепта


// Метод для удаления рецепта по его ID
