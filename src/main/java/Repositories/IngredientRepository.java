package Repositories;
import com.example.demo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    // Метод для поиска ингредиента по его названию
    Ingredient findByTitle(String title);

    // Метод для поиска ингредиентов по части названия (с использованием LIKE для частичного совпадения)
    List<Ingredient> findByTitleContainingIgnoreCase(String title);
}
