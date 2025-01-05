package org.moysha.lab4t.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.User;
import org.moysha.lab4t.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            System.out.println(username + " already exists");
            return false;
        }


        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

}
