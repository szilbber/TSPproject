package com.example.demo.Repositories;
import com.example.demo.Entity.CompositionRecipe;
import com.example.demo.Entity.Ingredient;
import com.example.demo.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompositionRecipeRepository extends JpaRepository<CompositionRecipe, Long> {

    // Найти все ингредиенты для данного рецепта
    List<CompositionRecipe> findByRecipe(Recipe recipe);

    // Найти все рецепты, использующие данный ингредиент
    List<CompositionRecipe> findByIngredient(Ingredient ingredient);

    // Найти запись о составе рецепта по рецепту и ингредиенту
    CompositionRecipe findByRecipeAndIngredient(Recipe recipe, Ingredient ingredient);

    // Удалить состав рецепта по рецепту и ингредиенту
    void deleteByRecipeAndIngredient(Recipe recipe, Ingredient ingredient);


}
