package com.example.demo.Repositories;
import com.example.demo.Entity.Ingredient;
import com.example.demo.Entity.ShoppingList;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    // Получить все покупки для конкретного пользователя
    List<ShoppingList> findByUser(User user);

    // Удалить все покупки для пользователя по статусу
    void deleteByUserAndStatus(User user, Boolean status);

    // Удалить одну покупку по пользователю, ингредиенту и статусу
    void deleteByUserAndIngredientAndStatus(User user, Ingredient ingredient, Boolean status);
}
