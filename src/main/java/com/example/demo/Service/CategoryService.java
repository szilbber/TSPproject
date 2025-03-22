package com.example.demo.Service;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    // Получить категорию по названию
    public Optional<Category> getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    // Получить категорию по id
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    // Получить список категорий по части названия
    public List<Category> searchCategoriesByTitle(String title) {
        return categoryRepository.findByTitleContainingIgnoreCase(title);
    }

    // Сохранить или обновить категорию
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

//    // Удалить категорию по ID
//    public void deleteCategory(int id) {
//        categoryRepository.deleteById(id);
//    }

    // Получить все категории
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
