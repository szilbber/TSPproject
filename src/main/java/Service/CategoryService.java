package Service;
import Repositories.CategoryRepository;
import com.example.demo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Получить категорию по названию
    public Category getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    // Получить список категорий по части названия
    public List<Category> searchCategoriesByTitle(String title) {
        return categoryRepository.findByTitleContainingIgnoreCase(title);
    }

    // Сохранить или обновить категорию
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Удалить категорию по ID
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    // Получить все категории
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
