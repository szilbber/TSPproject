package com.example.demo.Service;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.RecipeRepository;
import com.example.demo.Entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional //в одной транзакции
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    // Метод для получения рецепта по его ID
    public Optional<Recipe> getRecipeById(int id) {
        return recipeRepository.findById(id);
    }

    // Метод для получения всех рецептов пользователя по его ID
    public List<Recipe> getRecipesByUserId(User userId) {
        return recipeRepository.findByUserId(userId);
    }

    // Метод для получения всех рецептов по категории
    public List<Recipe> getRecipesByCategoryId(Category categoryId) {
        return recipeRepository.findByCategory(categoryId);
    }

    // Метод для поиска рецептов по части названия (с использованием LIKE)
    public List<Recipe> searchRecipesByTitle(String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    // Метод для создания или обновления рецепта
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // Метод для удаления рецепта по его ID
    public void deleteRecipe(int id) {
        recipeRepository.deleteById(id);
    }
}