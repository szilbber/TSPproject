package com.example.demo.Controllers;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.User;
import com.example.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    //Метод получения всех категорий
    @Autowired
    private CategoryService categoryService; // Внедрение сервиса категорий

    // Регистрация новой категории
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        System.out.println("создание категории1");
        Category createdCategory = categoryService.createCategory(category);
        System.out.println("создание категории2");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    // Получить все категории
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK); // Возвращаем список категорий со статусом 200 OK
    }
}
