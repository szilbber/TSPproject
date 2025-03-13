package Service;
import Repositories.ShoppingListRepository;
import com.example.demo.Ingredient;
import com.example.demo.ShoppingList;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    // Добавление нового элемента в список покупок
    @Transactional
    public ShoppingList addToShoppingList(User user, Ingredient ingredient, double quantity) {
        // Проверим, существует ли уже этот ингредиент в списке покупок для данного пользователя
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setIngredient(ingredient);
        shoppingList.setQuantity(quantity);
        shoppingList.setStatus(false);  // Статус по умолчанию: не куплен

        return shoppingListRepository.save(shoppingList);
    }

    // Получение всех покупок для конкретного пользователя
    public List<ShoppingList> getShoppingList(User user) {
        return shoppingListRepository.findByUser(user);
    }

    // Удаление всех покупок для пользователя с определенным статусом
    @Transactional
    public void deleteByStatus(User user, Boolean status) {
        shoppingListRepository.deleteByUserAndStatus(user, status);
    }

    // Удаление одной покупки по пользователю, ингредиенту и статусу
    @Transactional
    public void deleteShoppingItem(User user, Ingredient ingredient, Boolean status) {
        shoppingListRepository.deleteByUserAndIngredientAndStatus(user, ingredient, status);
    }

    // Обновление статуса покупки
    @Transactional
    public ShoppingList updateShoppingListStatus(User user, Ingredient ingredient, Boolean status) {
        // Найдем покупку, чтобы обновить ее статус
        List<ShoppingList> shoppingList = shoppingListRepository.findByUser(user);
        for (ShoppingList item : shoppingList) {
            if (item.getIngredient().equals(ingredient)) {
                item.setStatus(status);
                return shoppingListRepository.save(item);
            }
        }
        return null; // Если покупка не найдена
    }
}
