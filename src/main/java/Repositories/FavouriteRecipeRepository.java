package Repositories;
import com.example.demo.FavouriteRecipe;
import com.example.demo.Recipe;
import com.example.demo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRecipeRepository extends JpaRepository<FavouriteRecipe, Long> {
    // Найти все любимые рецепты пользователя
    List<FavouriteRecipe> findByUser(User user);

    // Найти все рецепты для конкретного пользователя и рецепта
    FavouriteRecipe findByUserAndRecipe(User user, Recipe recipe);

    // Удалить любимый рецепт по пользователю и рецепту
    void deleteByUserAndRecipe(User user, Recipe recipe);
}
