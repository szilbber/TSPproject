package com.example.demo.Service;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordService passwordUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordService passwordUtil) {
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
    }

    @Autowired
    private EntityManager entityManager;
    @Transactional
    // Регистрация пользователя и обновление
    public User registerUser(User user) {
//        if (userRepository.existsByName(user.getUsername())) {
//            // Заменить на свои исключения
//            throw new RuntimeException("Пользователь с таким именем уже существует");
//        }
//
//        if (userRepository.existsByMail(user.getMail())) {
//            throw new RuntimeException("Пользователь с таким email уже существует");
//        }

        // Хешируем пароль перед сохранением
//        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
//        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // Проверка пароля пользователя, то есть вход
    public boolean checkPassword(String rawPassword, String email) {
        User user = userRepository.findByMail(email);
        return user != null && passwordUtil.matches(rawPassword, user.getPassword());
    }

    // Метод для поиска пользователя по email
    public User getUserByEmail(String email) {
        return userRepository.findByMail(email);
    }

    // Метод для поиска пользователя по имени
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Метод для поиска пользователя по ID
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Метод для удаления пользователя по его ID
    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }
    public User updateUser(Integer id,User updatedUser) {

        User existingUser = getUserById(id);
        // При изменении пароля – хэшируем заново
        if (!existingUser.getPassword().equals(updatedUser.getPassword())) {
            existingUser.setPassword(passwordUtil.encodePassword(updatedUser.getPassword()));
        }

        existingUser.setBday(updatedUser.getBday());
        existingUser.setMail(updatedUser.getMail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setName(updatedUser.getUsername());

        return userRepository.save(existingUser);
    }


//    public boolean existsByUsername(String username) {
//        User user = userRepository.findByName(username).orElse(null);
//        if (user != null) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean existsByEmail(String email) {
//        User user = userRepository.findByEmail(email).orElse(null);
//        if (user != null) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}