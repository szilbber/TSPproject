package com.example.demo.Service;
import com.example.demo.Entity.Category;
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
    public CompositionRecipe createCompositionRecipe(CompositionRecipe compositionRecipe) {
        // Проверяем, если данный ингредиент уже существует в рецепте

            return compositionRecipeRepository.save(compositionRecipe);

    }

    //Апдейт состава рецепта
//    public CompositionRecipe updateCompRecipe(CompositionRecipe updatedCompRecipe) {
//
//    }

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
