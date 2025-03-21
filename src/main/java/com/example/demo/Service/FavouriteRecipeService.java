package com.example.demo.Service;
import com.example.demo.Repositories.FavouriteRecipeRepository;
import com.example.demo.Entity.FavouriteRecipe;
import com.example.demo.Entity.Recipe;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class


FavouriteRecipeService {

    private final FavouriteRecipeRepository favouriteRecipeRepository;

    @Autowired
    public FavouriteRecipeService(FavouriteRecipeRepository favouriteRecipeRepository) {
        this.favouriteRecipeRepository = favouriteRecipeRepository;
    }

    // Добавление рецепта в избранное
    @Transactional
    public FavouriteRecipe addToFavourite(User user, Recipe recipe) {
        // Проверим, если рецепт уже добавлен в избранное
        FavouriteRecipe favouriteRecipe = favouriteRecipeRepository.findByUserAndRecipe(user, recipe);
        if (favouriteRecipe == null) {
            favouriteRecipe = new FavouriteRecipe(user, recipe);
            return favouriteRecipeRepository.save(favouriteRecipe);
        }
        return null; // Если рецепт уже в избранном, ничего не делаем
    }

    // Удаление рецепта из избранного
    @Transactional
    public void removeFromFavourite(User user, Recipe recipe) {
        favouriteRecipeRepository.deleteByUserAndRecipe(user, recipe);
    }

    // Получение всех любимых рецептов пользователя
    public List<FavouriteRecipe> getFavouriteRecipes(User user) {
        return favouriteRecipeRepository.findByUser(user);
    }
}
