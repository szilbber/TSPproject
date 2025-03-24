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
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    void createRecipeWithIngredients() {
        // Создаем тестового пользователя, категорию (через репозитории)
        User user = new User();
        user.setName("Test User");
        userRepository.save(user);
        Category category = new Category();
        category.setTitle("Test Category");
        categoryRepository.save(category);


        // Создаем ингредиенты (через репозитории)
        Ingredient carrot = new Ingredient();
        carrot.setTitle("Морковь");
        carrot.setUnitMeasure("grams");
        ingredientRepository.save(carrot);

        Ingredient potato = new Ingredient();
        potato.setTitle("Картошка");
        potato.setUnitMeasure("grams");
        ingredientRepository.save(potato);

        Ingredient egg = new Ingredient();
        potato.setTitle("Яйцо");
        potato.setUnitMeasure("grams");
        ingredientRepository.save(egg);

        // Создаем рецепт
        Recipe recipe = new Recipe();
        recipe.setUser(user);
        recipe.setCategory(category);
        recipe.setTitle("Test Recipe");
        // ... другие поля рецепта

        Set<CompositionRecipe> ingredients = new HashSet<>();
        ingredients.add(new CompositionRecipe(recipe,carrot, 150.0));
        ingredients.add(new CompositionRecipe(recipe, potato, 200.0));
        ingredients.add(new CompositionRecipe(recipe, egg, 2));
        recipe.setIngredients(ingredients);

        // Сохраняем рецепт (через репозиторий)
        //recipeService.createRecipe(recipe);

        // Получаем сохраненный рецепт
        //Recipe savedRecipe = recipeRepository.findById(recipe.getId_recipe()).orElseThrow();
        Recipe savedRecipe = recipeService.createRecipe(recipe);;

        // Проверки
        assertNotNull(savedRecipe.getId_recipe());
        assertEquals(3, savedRecipe.getIngredients().size());
        assertNotEquals(2, savedRecipe.getIngredients().size());

    }


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
