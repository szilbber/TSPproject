package Service;
import Repositories.IngredientRepository;
import com.example.demo.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // Получить ингредиент по названию
    public Ingredient getIngredientByTitle(String title) {
        return ingredientRepository.findByTitle(title);
    }

    // Получить список ингредиентов по части названия
    public List<Ingredient> searchIngredientsByTitle(String title) {
        return ingredientRepository.findByTitleContainingIgnoreCase(title);
    }

//    // Сохранить или обновить ингредиент
//    public Ingredient saveIngredient(Ingredient ingredient) {
//        return ingredientRepository.save(ingredient);
//    }
//
//    // Удалить ингредиент по ID
//    public void deleteIngredient(int id) {
//        ingredientRepository.deleteById(id);
//    }
//
//    // Получить все ингредиенты
//    public List<Ingredient> getAllIngredients() {
//        return ingredientRepository.findAll();
//    }
}
