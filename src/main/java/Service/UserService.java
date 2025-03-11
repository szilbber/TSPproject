package Service;

import Repositories.UserRepository;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordUtil;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Регистрация пользователя
    public User registerUser(User user) {
        // Хешируем пароль перед сохранением
        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // Проверка пароля пользователя
    public boolean checkPassword(String rawPassword, String email) {
        User user = userRepository.findByMail(email);
        return passwordUtil.matches(rawPassword, user.getPassword());
    }

    // Метод для поиска пользователя по email
    public User getUserByEmail(String email) {
        return userRepository.findByMail(email);
    }

    // Метод для поиска пользователя по имени
    public Optional<User> getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    // Метод для поиска пользователя по ID
    public User getUserById(int id) {
        return userRepository.findUserByUserId(id);
    }

}