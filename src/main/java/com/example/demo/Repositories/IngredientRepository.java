package com.example.demo.Repositories;
import com.example.demo.Entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {


    Optional<Ingredient> findById(Integer id);

    // Метод для поиска ингредиента по его названию
   Optional<Ingredient> findByTitle(String title);

    // Метод для поиска ингредиента по его id
    Ingredient findById(int id);

    // Метод для поиска ингредиентов по части названия (с использованием LIKE для частичного совпадения)
    List<Ingredient> findByTitleContainingIgnoreCase(String title);
}
