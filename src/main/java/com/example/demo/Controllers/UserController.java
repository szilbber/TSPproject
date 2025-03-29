package com.example.demo.Controllers;
import com.example.demo.Service.UserService;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Регистрация нового пользователя
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User createdUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    // Получение пользователя по ID //обработать ошибку 500
    @GetMapping("id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);  // Получаем пользователя из базы
        return ResponseEntity.ok(user);  // Отправляем 200 OK с объектом user
    }
    //Удаление пользователя
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Юзер успешно удален");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при удалении юреза: " + e.getMessage());
        }
    }

    @PutMapping("/{id}") // Добавляем путь для идентификатора пользователя
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user); // Передаем id для обновления конкретного пользователя
//        user.setId_user(id);
        return new ResponseEntity<>(user, HttpStatus.OK); // Возвращаем статус 200 OK
    }

//    // Получение пользователя по имени
//    @GetMapping("/name/{name}")
//    public ResponseEntity<User> getUserByName(@PathVariable String name) {
//        return ResponseEntity.ok(userService.getUserByName(name));
//    }
//    // Получение пользователя по email
//    @GetMapping("/email/{email}")
//    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
//        User user = userService.getUserByEmail(email);
//        if (user != null) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    // Проверка пароля пользователя по email
//    @PostMapping("/check-password")
//    public ResponseEntity<String> checkPassword(@RequestParam String email, @RequestParam String password) {
//        boolean isPasswordValid = userService.checkPassword(password, email);
//        if (isPasswordValid) {
//            return new ResponseEntity<>("Password is correct", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Password is incorrect", HttpStatus.BAD_REQUEST);
//        }
//    }





}
