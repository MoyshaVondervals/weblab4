package org.moysha.lab4t.controllers;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.AuthResponseDto;
import org.moysha.lab4t.models.UserDto;
import org.moysha.lab4t.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;



    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        return authService.register(username, password);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        return authService.login(username, password);
    }

}