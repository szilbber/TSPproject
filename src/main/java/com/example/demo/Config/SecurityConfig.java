package com.example.demo.Config;

import com.example.demo.Filter.JwtFilter;
import com.example.demo.Handler.CustomAccessDeniedHandler;
import com.example.demo.Handler.CustomLogoutHandler;
import com.example.demo.Service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFIlter;
    private final UserService userService;
    private final CustomLogoutHandler customLogoutHandler;

    public SecurityConfig(JwtFilter jwtFIlter, UserService userService, CustomLogoutHandler customLogoutHandler) {
        this.jwtFIlter = jwtFIlter;
        this.userService = userService;
        this.customLogoutHandler = customLogoutHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Отключаем CSRF для REST API
        http.csrf(AbstractHttpConfigurer::disable);

        // Настройка доступа
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/a/login", "/a/registration", "/a/refresh_token").permitAll();  // Разрешаем доступ к этим эндпоинтам всем
                    auth.requestMatchers("/api/categories/create").hasRole("ADMIN");  // Только для админов
                    auth.anyRequest().authenticated();  // Для остальных запросов требуется аутентификация
                })
                .userDetailsService(userService)  // Настройка кастомного сервиса для работы с пользователями
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))  // Статус сессии — без сохранения
                .addFilterBefore(jwtFIlter, UsernamePasswordAuthenticationFilter.class)  // Добавляем JWT фильтр
                .logout(log -> {
                    log.logoutUrl("/logout");
                    log.addLogoutHandler(customLogoutHandler);  // Обработчик логаута
                    log.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());  // Очищаем контекст безопасности после логаута
                });

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Шифрование пароля с использованием BCrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();  // Получаем менеджер аутентификации
    }
}