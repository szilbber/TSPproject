package com.example.demo.Controllers;
import com.example.demo.Dto.CompositionRecipeDTO;
import com.example.demo.Dto.IngredientDTO;
import com.example.demo.Dto.RecipeDTO;
import com.example.demo.Entity.*;
import com.example.demo.Exeptions.ResourceNotFoundException;
import com.example.demo.Repositories.*;
import com.example.demo.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CompositionRecipeRepository compositionRecipeRepository;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO request) {
        // Создаем основной объект рецепта
        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setManual(request.getManual());
        recipe.setTime(request.getTime());

        User user = userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(()-> new RuntimeException("Category not found"));

        recipe.setUser(user); //вощвращшаем афди а нужно юзер
        recipe.setCategory(category);

        // Создаем список ингредиентов для рецепта
        Set<CompositionRecipe> ingredients = new HashSet<>();

        // Для каждого ингредиента создаем объект CompositionRecipe
        for (CompositionRecipeDTO ingredientRequest : request.getIngredients()) {
            CompositionRecipe composition = new CompositionRecipe();
            Ingredient ingredient = ingredientRepository.findById(ingredientRequest.getIngredientId())
                            .orElseThrow(()-> new RuntimeException("Ingredient not found"));
            composition.setIngredient(ingredient);
            composition.setQuantity(ingredientRequest.getQuantity());
            composition.setRecipe(recipe);

            ingredients.add(composition);
        }

        // Устанавливаем связь между рецептом и ингредиентами
        recipe.setIngredients(ingredients);

        // Сохраняем рецепт
        Recipe savedRecipe = recipeService.createRecipe(recipe);

        return ResponseEntity.created(URI.create("/recipes/" + savedRecipe.getId_recipe()))
                .body(savedRecipe);
    }
}