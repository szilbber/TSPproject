package com.example.demo.Repositories;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Метод для поиска пользователя по email
    User findByMail(String mail);
    // Метод для поиска пользователя по имени
    Optional<User> findByName(String name);
    //Поиск пользователя по айди
    Optional<User> findById(Integer id_user);
    boolean existsByName(String name);
    boolean existsByMail(String mail);

}