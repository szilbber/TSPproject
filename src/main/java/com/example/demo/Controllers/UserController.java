package com.example.demo.Controllers;
import com.example.demo.Dto.RecipeAnswerDTO;
import com.example.demo.Dto.UserProfileDTO;
import com.example.demo.Entity.Recipe;
import com.example.demo.Repositories.RecipeRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Service.RecipeService;
import com.example.demo.Service.UserService;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")//ппросмотр своего профиля
    public ResponseEntity<UserProfileDTO> getUserById() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = (User) userService.loadUserByUsername(userDetails.getUsername());
            UserProfileDTO profileDTO=new UserProfileDTO();
            profileDTO.setUsername(currentUser.getUsername());
            profileDTO.setEmail(currentUser.getMail());
            profileDTO.setPhone(currentUser.getPhone());
            profileDTO.setBday(currentUser.getBday());

            return ResponseEntity.ok(profileDTO);  // Отправляем 200 OK с объектом user

    }
    //Удаление пользователя
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Пользователь с id " + id + " не найден");
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204, если успешно удалён
    }

    @PutMapping("/{id}") // Добавляем путь для идентификатора пользователя
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user); // Передаем id для обновления конкретного пользователя
//        user.setId_user(id);
        return new ResponseEntity<>(user, HttpStatus.OK); // Возвращаем статус 200 OK
    }

    // Метод для добавления рецепта в избранное
    @PostMapping("/favourite-recipes/{recipeId}")
    public ResponseEntity<Void> addFavouriteRecipe(@PathVariable Integer recipeId) {
        // Получаем пользователя и рецепт по ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = (User) userService.loadUserByUsername(userDetails.getUsername());
        Recipe recipe = recipeService.getRecipeById(recipeId);

        // Добавляем рецепт в избранное пользователя
        currentUser.getFavouriteRecipes().add(recipe);

        // Сохраняем изменения в базе данных
        userRepository.save(currentUser);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/myFavouriteRecipe")//мои любимые рецепты
    public ResponseEntity<List<RecipeAnswerDTO>> getMyFavRecipe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = (User) userService.loadUserByUsername(username);
        Set<Recipe> recipes = currentUser.getFavouriteRecipes();

        List<RecipeAnswerDTO> recipeDTOs = recipes.stream()
                .map(r -> new RecipeAnswerDTO(r.getId(), r.getTitle(), r.getTime()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(recipeDTOs);
    }
    @GetMapping("/myRecipe")//список моих рецептов
    public ResponseEntity<List<RecipeAnswerDTO>> getMyRecipe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = (User) userService.loadUserByUsername(username);
        Set<Recipe> recipes = currentUser.getRecipes();

        List<RecipeAnswerDTO> recipeDTOs = recipes.stream()
                .map(r -> new RecipeAnswerDTO(r.getId(), r.getTitle(), r.getTime()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(recipeDTOs);
    }



    @DeleteMapping("/removeFavourite/{recipeId}")//удаление рцеепта из избранного
    public ResponseEntity<String> removeFavouriteRecipe( @PathVariable Integer recipeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = (User) userService.loadUserByUsername(userDetails.getUsername());

        Recipe recipe = recipeService.getRecipeById(recipeId);

        // Проверяем, есть ли рецепт в избранных
        if (currentUser.getFavouriteRecipes().contains(recipe)) {
            // Удаляем рецепт из коллекции избранных рецептов
            currentUser.getFavouriteRecipes().remove(recipe);

            // Сохраняем пользователя (Hibernate автоматически обновит таблицу связи)
            userService.registerUser(currentUser);

            return ResponseEntity.ok("Рецепт удален из избранного");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Рецепт не удалось удалить из зибранного");
        }
    }

}






