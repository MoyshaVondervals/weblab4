package org.moysha.lab4t.service;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.AppUser;
import org.moysha.lab4t.models.AuthRespForm;
import org.moysha.lab4t.repository.UserRepository;
import org.moysha.lab4t.security.JwtCore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;


    public ResponseEntity<String> register(String username, String password) {
        System.err.println("Register start work");
        if (userRepository.existsByUsername(username)) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        System.err.println("register success");
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    public ResponseEntity<AuthRespForm> login(String username, String password) {
        System.err.println("Login start work");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtCore.generateToken(authentication);
        System.err.println("login success");
        return new ResponseEntity<>(new AuthRespForm(token, username), HttpStatus.OK);
    }
}