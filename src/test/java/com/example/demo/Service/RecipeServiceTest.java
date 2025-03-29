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
    @Autowired
    private CategoryService categoryService;


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

        String mail = "ammail@example.com";
        String password = "passpassword";
        String name = "Dan";
        String phone = "8999418337";
        LocalDate bday = LocalDate.of(2005, 7, 20);
        User user = new User( mail, password, name, phone, bday);
        User userId= userService.registerUser(user);

        String title_category = "Выпечка";
        Category category = new Category(title_category);
        Category category_recipe =categoryService.createCategory(category);;



        Recipe recipe = new Recipe(userId, category_recipe, "Оливье", "Описание рецепта", "Инструкция по приготовлению", "30 минут");


        assertEquals(recipeService.createRecipe(recipe).getTitle(), "Оливье");

//        //обновление рецепта
//        recipe_new.setId_recipe(recipe.getId_recipe());
//        assertEquals(recipeService.createRecipe(recipe_new).getTitle(), "Борщ");

//        // Метод для удаления рецепта по его ID
//        recipeService.deleteRecipe(recipe.getId_recipe());
//        assertThat(recipeService.getRecipeById(recipe.getId_recipe())).isEmpty();

    }

}
