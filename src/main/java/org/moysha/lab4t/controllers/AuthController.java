package org.moysha.lab4t.controllers;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.AuthRespForm;
import org.moysha.lab4t.models.UserForm;
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
    public ResponseEntity<String> register(@RequestBody UserForm userForm) {
        String username = userForm.getUsername();
        String password = userForm.getPassword();
        return authService.register(username, password);
    }

    @PostMapping("login")
    public ResponseEntity<AuthRespForm> login(@RequestBody UserForm userForm) {
        String username = userForm.getUsername();
        String password = userForm.getPassword();
        return authService.login(username, password);
    }

}