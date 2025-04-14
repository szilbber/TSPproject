package com.example.demo.Controllers;

import com.example.demo.Dto.AuthenticationResponseDto;
import com.example.demo.Dto.LoginRequestDto;
import com.example.demo.Dto.RegistrationRequestDto;
import com.example.demo.Service.AuthenticationService;
import com.example.demo.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/a")
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authenticationService;

    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }
    @PostMapping("/registration")
    public ResponseEntity<List<String>> register(
            @Valid
            @RequestBody RegistrationRequestDto registrationDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        authenticationService.register(registrationDto);

        return ResponseEntity.ok(Collections.singletonList("Регистрация прошла успешно"));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody LoginRequestDto request) {
        System.out.println(",f,f,f,,f");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {

        return authenticationService.refreshToken(request, response);
    }

}
