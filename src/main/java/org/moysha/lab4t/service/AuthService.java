package org.moysha.lab4t.service;

import org.moysha.lab4t.models.AuthResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(String username, String password);
    ResponseEntity<AuthResponseDto> login(String username, String password);
}