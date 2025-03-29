package com.example.demo.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;  // Должен быть внедрен бин UserService

    String title = "Второе";
    Category category = new Category(title);

    String title_new = "Салаты";
    Category category_new = new Category(title_new);

    @Test
    public void testCategory() {
        assertNotNull(categoryService.createCategory(category).getId_category());
//
//        assertEquals(categoryService.getCategoryByTitle("Выпечка").get().getId_category(), category.getId_category());
//
//        category_new.setId_category(category.getId_category());
//        assertEquals(categoryService.createCategory(category_new).getTitle(), "Салаты");
////
//
//        assertEquals(categoryService.getCategoryById(category.getId_category()).get().getId_category(), category.getId_category());
//
////
//       categoryService.deleteCategory(category.getId_category());
//        assertThat(categoryService.getCategoryById(category.getId_category())).isEmpty();


    }
}
