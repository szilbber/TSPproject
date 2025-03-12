package Repositories;
import com.example.demo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Метод для поиска категории по названию
    Category findByTitle(String title);

    // Метод для поиска категорий по части названия (с использованием LIKE для частичного совпадения)
    List<Category> findByTitleContainingIgnoreCase(String title);
}
