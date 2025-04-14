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

    @Query("SELECT DISTINCT r FROM Recipe r " +
            "LEFT JOIN r.ingredients cr " +
            "LEFT JOIN cr.ingredient i " +
            "WHERE (:categoryId IS NULL OR r.category.id = :categoryId) ")
    List<Recipe> findByFilters(
            //@Param("title") String title,
            @Param("categoryId") Long categoryId
           // @Param("ingredientNames") List<String> ingredientNames
    );
//
//    // Пользовательский запрос для поиска рецептов по пользователю и названию с использованием LIKE
//    @Query("SELECT r FROM Recipe r WHERE r.user.id = :userId AND r.title LIKE %:title%")
//    List<Recipe> findByUserIdAndTitleLike(int userId, String title);
//
//    // Пользовательский запрос для поиска рецептов по описанию и времени
//    @Query("SELECT r FROM Recipe r WHERE r.description LIKE %:description% AND r.time = :time")
//    List<Recipe> findByDescriptionAndTime(String description, String time);
}
