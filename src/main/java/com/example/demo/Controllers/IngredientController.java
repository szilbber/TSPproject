package com.example.demo.Controllers;


import com.example.demo.Entity.Category;
import com.example.demo.Entity.Ingredient;
import com.example.demo.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService; // Внедрение сервиса ингредиентов

    // создание нового ингредиента
    @PostMapping("/create")
    public ResponseEntity<?> createIngredient(@RequestBody Ingredient ingredient) {

            Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIngredient);

    }
    // Получить все ингредиенты
    @GetMapping
    public ResponseEntity<?> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients(); // Получаем список ингредиентов
        try {
            return new ResponseEntity<>(ingredients, HttpStatus.OK); // Возвращаем список ингредиентов со статусом 200 OK

        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка на сервере");
        }
    }
}
