package com.example.demo.Service;
import com.example.demo.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;  // Должен быть внедрен бин UserService

    String mail = "mail@example.com";
    String password = "password";
    String name = "name";
    String phone = "89179418337";
    LocalDate bday = LocalDate.of(1985, 7, 20);
    User user = new User( mail, password, name, phone, bday);

    @Test
    public void testUser() {
        assertNotNull(userService.registerUser(user).getId_user());
        assertNotNull(userService.getUserByEmail("mail@example.com"));
        assertTrue(userService.checkPassword("password", "mail@example.com" ));
        assertEquals(userService.getUserByName("name").get().getId_user(), user.getId_user());
        assertEquals(userService.getUserById(user.getId_user()).get().getId_user(), user.getId_user());
    }


}