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

    //создание категории
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    // Получить категорию по названию
    public Category getCategoryByTitle(String title) {
        Optional<Category> categoryOpt = categoryRepository.findByTitle(title);
        if (categoryOpt.isEmpty()) {
            throw new RuntimeException("Category not found: " + title);
        }
        return categoryOpt.get();
    }


    // Получить категорию по id
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Получить список категорий по части названия
    public List<Category> searchCategoriesByTitle(String title) {
        return categoryRepository.findByTitleContainingIgnoreCase(title);
    }

    //Апдейт категории
    public Category updateCategory(Category updatedCategory) {

        Category existingCategory = getCategoryById(updatedCategory.getId_category());
        existingCategory.setTitle(updatedCategory.getTitle());
        return categoryRepository.save(existingCategory);
    }

    // Получить все категории
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Метод для удаления категории по его ID
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
