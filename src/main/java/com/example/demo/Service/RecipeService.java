package com.example.demo.Service;
import com.example.demo.Dto.RecipeAnswerDTO;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.CompositionRecipeRepository;
import com.example.demo.Repositories.RecipeRepository;
import com.example.demo.Entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional //в одной транзакции
public class RecipeService {
    private final RecipeRepository recipeRepository;
    @Autowired
    private CompositionRecipeRepository compositionRecipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    // Метод для получения рецепта по его ID
    @Transactional
    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    // Метод для получения всех рецептов пользователя по его ID
    @Transactional
    public List<Recipe> getRecipesByUserId(User userId) {

        return recipeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Recipes not found"));
    }

    // Метод для получения всех рецептов по категории
    @Transactional
    public Optional<List<Recipe>> getRecipesByCategoryId(Category categoryId) {
        return recipeRepository.findByCategory(categoryId);
    }

    // Метод для поиска рецептов по части названия (с использованием LIKE)
    public Optional<List<Recipe>>searchRecipesByTitle(String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    // Метод для удаления рецепта по его ID
    public void deleteRecipe(int id) {
        recipeRepository.deleteById(id);
    }

    //Апдейт рецепта
    public Recipe updateRecipe(Recipe updatedRecipe) {

        Recipe existingRecipe = getRecipeById(updatedRecipe.getId());
        existingRecipe.setTitle(updatedRecipe.getTitle());
        existingRecipe.setDescription(updatedRecipe.getDescription());
        existingRecipe.setManual(updatedRecipe.getManual());
        existingRecipe.setTime(updatedRecipe.getTime());
        return recipeRepository.save(existingRecipe);
    }

    public List<RecipeAnswerDTO> searchRecipes(String title, Long categoryId, List<String> ingredients) {
        List<Recipe> recipes = recipeRepository.findByFilters(title,categoryId, ingredients);
        return recipes.stream()
                .map(r -> new RecipeAnswerDTO(r.getId(), r.getTitle(), r.getTime()))
                .collect(Collectors.toList());
    }


//    public List<RecipeDTO> searchRecipeDTOs(Long categoryId) {
//        List<Recipe> recipes = recipeRepository.findByFilters(categoryId);
//
//        return recipes.stream().map(recipe -> {
//            String categoryName = recipe.getCategory() != null ? recipe.getCategory().getTitle() : null;
//
//            List<IngredientDTO> ingredientTitles = recipe.getIngredients().stream()
//                    .map(comp -> comp.getIngredient().getTitle())
//                    .toList();
//
//            return new RecipeDTO(
//                    recipe.getId(),
//                    recipe.getTitle(),
//                    recipe.getDescription(),
//                    recipe.getManual(),
//                    recipe.getTime(),
//                    ingredientTitles
//            );
//        }).toList();
//    }
}