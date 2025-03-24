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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class RecipeServiceTest {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    String mail = "ammail@example.com";
    String password = "passpassword";
    String name = "Dan";
    String phone = "8999418337";
    LocalDate bday = LocalDate.of(2005, 7, 20);
    User user = new User( mail, password, name, phone, bday);

    String title_category = "Выпечка";
    Category category = new Category(title_category);

    User userId= user;
    Category category_recipe = category;
    String title_recipe = "Блины";
    String description = "Очень вкусные блины";
    String manual = "Печь, есть, балдеть";
    String time = "30 минут";
    byte[] picture;

    Recipe recipe = new Recipe();

    @Test
    public void testRecipe() {

        userService.registerUser(user);
        // Метод для создания или обновления рецепта
        assertNotNull(recipeService.createRecipe(recipe));

       // Метод для получения рецепта по его ID
        assertEquals(recipeService.getRecipeById(recipe.getId_recipe()).get().getId_recipe(), recipe.getId_recipe());

        // Метод для получения всех рецептов пользователя по его ID
        //ssertEquals(recipeService.getRecipesByUserId(user).get().getFirst().getId_recipe(), recipe.getId_recipe());

//        // Метод для получения всех рецептов по категории
//        assertEquals(recipeService.getRecipesByCategoryId(category_recipe).get().getFirst().getId_recipe(), recipe.getId_recipe());
//
//        // Метод для поиска рецептов по части названия (с использованием LIKE)
//
        // Метод для удаления рецепта по его ID
        recipeService.deleteRecipe(recipe.getId_recipe());
        assertThat(recipeService.getRecipeById(recipe.getId_recipe())).isEmpty();
    }
}
