package com.example.demo;
import Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAllUsers() {
        // Проверка, что данные были добавлены в базу данных
        long userCount = userRepository.count();
        assertEquals(2, userCount);
    }

    @Test
    void testFindByName() {
        // Проверка, что пользователь был добавлен в базу данных
        User foundUser = userRepository.findUserByName("John");
        assertNotNull(foundUser);
    }
}