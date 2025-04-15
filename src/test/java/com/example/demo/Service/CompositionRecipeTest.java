package com.example.demo.Service;

import com.example.demo.Entity.*;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.IngredientRepository;
import com.example.demo.Repositories.RecipeRepository;
import com.example.demo.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CompositionRecipeTest {

    @Autowired
    private CompositionRecipeService compositionService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testComposition() {

        Ingredient ingredient = new Ingredient("Масло","Граммы");

        User user = new User();
        user.setName("User1");
        userRepository.save(user);

        Category category = new Category();
        category.setTitle("Пироги");
        categoryRepository.save(category);

        Recipe recipe = new Recipe(user, category, "Штрудель", "Описание рецепта", "Инструкция по приготовлению", "30 минут");

        ingredientRepository.save(ingredient);
        recipeRepository.save(recipe);
        CompositionRecipe comp_rec=new CompositionRecipe(recipe,ingredient, 500);


//        assertNotNull(compositionService.createCompositionRecipe(comp_rec).getId());

//        compositionService.removeIngredientFromRecipe(recipe,ingredient);
//        assertThat(categoryService.getCategoryById(category.getId_category())).isEmpty();


    }
}
