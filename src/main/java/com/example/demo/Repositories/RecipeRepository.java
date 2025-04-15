package com.example.demo.Repositories;


import com.example.demo.Entity.Category;
import com.example.demo.Entity.Recipe;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    // Метод для поиска рецепта по его id
    Optional<Recipe> findById(int id_recipe);

    // Метод для поиска рецептов по пользователю (id_user)
    Optional<List<Recipe>> findByUserId(User userId);

    // Метод для поиска рецептов по категории (id_category)
    Optional<List<Recipe>> findByCategory(Category categoryId);

    // Метод для поиска рецептов по названию (с использованием LIKE для частичного совпадения)
    Optional<List<Recipe>> findByTitleContainingIgnoreCase(String title);

@Query("""
    SELECT DISTINCT r FROM Recipe r
    JOIN r.ingredients cr
    JOIN cr.ingredient i
    WHERE
        (:title IS NULL OR r.title = :title) AND
        (:categoryId IS NULL OR r.category.id = :categoryId) AND
        (:ingredients IS NULL OR i.title IN :ingredients)
""")
//@Query("""
//    SELECT r FROM Recipe r
//    JOIN r.ingredients cr
//    JOIN cr.ingredient i
//    WHERE
//        (:title IS NULL OR r.title = :title) AND
//        (:categoryId IS NULL OR r.category.id = :categoryId) AND
//        (:ingredients IS NULL OR i.title IN :ingredients)
//    GROUP BY r
//    HAVING COUNT(DISTINCT i.title) = :#{#ingredients == null ? 0 : #ingredients.size()}
//""")
    List<Recipe> findByFilters(@Param("title") String title,
                               @Param("categoryId") Long categoryId,
                               @Param("ingredients") List<String> ingredients);
}
