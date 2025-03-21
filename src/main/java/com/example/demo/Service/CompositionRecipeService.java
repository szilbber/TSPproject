package com.example.demo.Service;
import com.example.demo.Repositories.CompositionRecipeRepository;
import com.example.demo.Entity.CompositionRecipe;
import com.example.demo.Entity.Ingredient;
import com.example.demo.Entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CompositionRecipeService {

    private final CompositionRecipeRepository compositionRecipeRepository;

    @Autowired
    public CompositionRecipeService(CompositionRecipeRepository compositionRecipeRepository) {
        this.compositionRecipeRepository = compositionRecipeRepository;
    }

    // Добавление ингредиента в рецепт
    @Transactional
    public CompositionRecipe addIngredientToRecipe(Recipe recipe, Ingredient ingredient, double quantity) {
        // Проверяем, если данный ингредиент уже существует в рецепте
        CompositionRecipe compositionRecipe = compositionRecipeRepository.findByRecipeAndIngredient(recipe, ingredient);
        if (compositionRecipe == null) {
            compositionRecipe = new CompositionRecipe();
            compositionRecipe.setRecipe(recipe);
            compositionRecipe.setIngredient(ingredient);
            compositionRecipe.setQuantity(quantity);
            return compositionRecipeRepository.save(compositionRecipe);
        }
        return null; // Если ингредиент уже в рецепте, ничего не делаем
    }

    // Удаление ингредиента из рецепта
    @Transactional
    public void removeIngredientFromRecipe(Recipe recipe, Ingredient ingredient) {
        compositionRecipeRepository.deleteByRecipeAndIngredient(recipe, ingredient);
    }

    // Получение всех ингредиентов для рецепта
    public List<CompositionRecipe> getIngredientsForRecipe(Recipe recipe) {
        return compositionRecipeRepository.findByRecipe(recipe);
    }

    // Получение всех рецептов с определенным ингредиентом
    public List<CompositionRecipe> getRecipesWithIngredient(Ingredient ingredient) {
        return compositionRecipeRepository.findByIngredient(ingredient);
    }
}
