package com.example.demo.Controllers;
import com.example.demo.Dto.IngredientDTO;
import com.example.demo.Dto.RecipeDTO;
import com.example.demo.Entity.*;
import com.example.demo.Repositories.*;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.IngredientService;
import com.example.demo.Service.RecipeService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
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

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/create")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO request) {
        // Создаем основной объект рецепта
        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setManual(request.getManual());
        recipe.setTime(request.getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User currentUser = (User) userService.loadUserByUsername(userDetails.getUsername());

        // Получаем пользователя и категорию (предполагается, что они уже существуют)
       // User user = userService.getUserById(request.getUserId());
        Category category = categoryService.getCategoryById(request.getCategoryId());

        recipe.setUser(currentUser);
        recipe.setCategory(category);

        // Создаем список компонентов для рецепта
        Set<CompositionRecipe> compositionRecipes = new HashSet<>();

        // Для каждого ингредиента создаем CompositionRecipe
        for (IngredientDTO ingredient : request.getIngredients()) {
            CompositionRecipe composition = new CompositionRecipe();

            // Получаем ингредиент из базы данных
            Ingredient ingr = ingredientService.getIngredientById(ingredient.getIngredientId());

            composition.setIngredient(ingr);
            composition.setQuantity(ingredient.getQuantity());
            composition.setRecipe(recipe);

            compositionRecipes.add(composition);
        }

        // Устанавливаем связь с компонентами
        recipe.setIngredients(compositionRecipes);

        // Сохраняем рецепт
        Recipe savedRecipe = recipeService.createRecipe(recipe);

        return ResponseEntity.created(URI.create("/recipes/" + savedRecipe.getId()))
                .body(savedRecipe);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(
           // @RequestParam(required = false) String title,
            @RequestParam(required = false) Long categoryId
            //@RequestParam(required = false) List<String> ingredients
    ) {
        System.out.println("jjjjjjjjjj");
        List<Recipe> recipes = recipeService.searchRecipes(categoryId);
        System.out.println("aaaaaaaaaaalll");
        return ResponseEntity.ok(recipes);
    }

}