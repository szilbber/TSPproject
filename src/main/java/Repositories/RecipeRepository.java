package Repositories;


import com.example.demo.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    // Метод для поиска рецепта по его id
    Optional<Recipe> findById(int id_recipe);

    // Метод для поиска рецептов по пользователю (id_user)
    List<Recipe> findByUserId(int userId);

    // Метод для поиска рецептов по категории (id_category)
    List<Recipe> findByCategoryId(int categoryId);

    // Метод для поиска рецептов по названию (с использованием LIKE для частичного совпадения)
    List<Recipe> findByTitleContainingIgnoreCase(String title);


//
//    // Пользовательский запрос для поиска рецептов по пользователю и названию с использованием LIKE
//    @Query("SELECT r FROM Recipe r WHERE r.user.id = :userId AND r.title LIKE %:title%")
//    List<Recipe> findByUserIdAndTitleLike(int userId, String title);
//
//    // Пользовательский запрос для поиска рецептов по описанию и времени
//    @Query("SELECT r FROM Recipe r WHERE r.description LIKE %:description% AND r.time = :time")
//    List<Recipe> findByDescriptionAndTime(String description, String time);
}
