package com.example.demo.Service;

import com.example.demo.Entity.*;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.IngredientRepository;
import com.example.demo.Repositories.RecipeRepository;
import com.example.demo.Repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Test
    void testRecipe() {
        // Создаем тестового пользователя, категорию (через репозитории)
       // User user = new User();
        //user.setName("Test User");
        User user2 = userRepository.save(user);

        //Category category = new Category();
       // category.setTitle("Test Category");
        Category cat2 = categoryRepository.save(category);


        Recipe recipe = new Recipe(user2, cat2, "Оливье", "Описание рецепта", "Инструкция по приготовлению", "30 минут");

        Recipe recipe_new = new Recipe(user2, cat2, "Борщ", "Описание рецепта", "Инструкция по приготовлению", "30 минут");

        //создание рецепта
        assertNotNull(recipeService.createRecipe(recipe));
        assertEquals(recipeService.createRecipe(recipe).getTitle(), "Оливье");

        //обновление рецепта
        recipe_new.setId_recipe(recipe.getId_recipe());
        assertEquals(recipeService.createRecipe(recipe_new).getTitle(), "Борщ");

//        // Метод для удаления рецепта по его ID
//        recipeService.deleteRecipe(recipe.getId_recipe());
//        assertThat(recipeService.getRecipeById(recipe.getId_recipe())).isEmpty();

    }

}
