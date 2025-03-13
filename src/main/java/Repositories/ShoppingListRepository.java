package Repositories;
import com.example.demo.Ingredient;
import com.example.demo.ShoppingList;
import com.example.demo.User;
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
