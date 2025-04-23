package com.example.demo.Config;

import com.example.demo.Filter.JwtFilter;
import com.example.demo.Handler.CustomAccessDeniedHandler;
import com.example.demo.Handler.CustomAuthenticationEntryPoint;
import com.example.demo.Handler.CustomLogoutHandler;
import com.example.demo.Service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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

        http.csrf(AbstractHttpConfigurer::disable);

        http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 401 Unauthorized
                        .accessDeniedHandler(new CustomAccessDeniedHandler())            // 403 Forbidden
                );

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/recipes","/favourite_recipes","/profile","/main","/login","/registration","/api/ingredients","/api/categories", "/api/recipes/search","/api/recipes/getRecipe/{id}", "/a/login", "/a/registration", "/a/refresh_token").permitAll()
                .requestMatchers("/api/categories/create", "/api/ingredients/create", "/api/users/id/{id}").hasRole("ADMIN")
                .anyRequest().authenticated());

        http
                .userDetailsService(userService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFIlter, UsernamePasswordAuthenticationFilter.class)
                .logout(log -> log.logoutUrl("/logout")
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

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