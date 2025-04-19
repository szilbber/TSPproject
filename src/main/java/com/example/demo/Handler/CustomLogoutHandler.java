package com.example.demo.Handler;

import com.example.demo.Entity.Token;
import com.example.demo.Repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Токен отсутствует или имеет неверный формат");
        }

        String token = authHeader.substring(7);
        Token tokenEntity = tokenRepository.findByAccessToken(token).orElse(null);

        // Проверяем: токен не найден или уже помечен как вышедший
        if (tokenEntity == null || tokenEntity.isLoggedOut()) {
            throw new AccessDeniedException("Токен недействителен или уже использован");
        }

        // Валидный токен — отмечаем как вышедший
        tokenEntity.setLoggedOut(true);
        tokenRepository.save(tokenEntity);
    }


}
