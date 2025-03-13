package Controllers;
import Service.UserService;
import com.example.demo.User;
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
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Проверка пароля пользователя по email
    @PostMapping("/check-password")
    public ResponseEntity<String> checkPassword(@RequestParam String email, @RequestParam String password) {
        boolean isPasswordValid = userService.checkPassword(password, email);
        if (isPasswordValid) {
            return new ResponseEntity<>("Password is correct", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Password is incorrect", HttpStatus.BAD_REQUEST);
        }
    }

    // Получение пользователя по email
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение пользователя по имени
    @GetMapping("/name")
    public ResponseEntity<User> getUserByName(@RequestParam String name) {
        Optional<User> userOptional = userService.getUserByName(name);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Получение пользователя по ID
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
