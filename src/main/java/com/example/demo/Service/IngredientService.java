package com.example.demo.Service;
import com.example.demo.Entity.Category;
import com.example.demo.Repositories.IngredientRepository;
import com.example.demo.Entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    //Создание ингредиента
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
    // Получить ингредиент по названию
    public Optional<Ingredient> getIngredientByTitle(String title) {
        return ingredientRepository.findByTitle(title);
    }

    // Получить ингредиент по id
    public Ingredient getIngredientById(Integer id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }


    // Метод для удаления ингредиента по его ID
    public void deleteIngredient(int id) {
        ingredientRepository.deleteById(id);
    }

    //Апдейт ингредиента
    public Ingredient updateIngredient(Ingredient updatedIngredient) {

        Ingredient existingIngredient = getIngredientById(updatedIngredient.getId_ingredient());
        existingIngredient.setTitle(updatedIngredient.getTitle());
        existingIngredient.setUnitMeasure(updatedIngredient.getUnitMeasure());
        return ingredientRepository.save(existingIngredient);
    }

    // Получить все ингредиенты
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
