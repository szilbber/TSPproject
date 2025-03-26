package com.example.demo.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class IngredientServiceTest {
    @Autowired
    private IngredientService ingredientService;  // Должен быть внедрен бин UserService

    String title = "Мука";
    String unit_measure="Граммы";
    Ingredient ingredient = new Ingredient(title,unit_measure);

    String title_new = "Молоко";
    String unit_measure_new="Милилитры";
    Ingredient ingredient_new = new Ingredient(title_new,unit_measure_new);

    @Test
    public void testIngredient() {
        assertNotNull(ingredientService.createIngredient(ingredient).getId_ingredient());
        assertEquals(ingredientService.getIngredientByTitle("Мука").get().getId_ingredient(), ingredient.getId_ingredient());
        assertEquals(ingredientService.getIngredientById(ingredient.getId_ingredient()).get().getId_ingredient(), ingredient.getId_ingredient());

        ingredient_new.setId_ingredient(ingredient.getId_ingredient());
        assertEquals(ingredientService.createIngredient(ingredient_new).getTitle(), "Молоко");

        ingredientService.deleteIngredient(ingredient.getId_ingredient());
        assertThat(ingredientService.getIngredientById(ingredient.getId_ingredient())).isEmpty();
    }
}
