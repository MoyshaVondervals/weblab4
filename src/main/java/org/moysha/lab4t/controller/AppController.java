package org.moysha.lab4t.controller;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.Application;
import org.moysha.lab4t.models.User;
import org.moysha.lab4t.services.AppService;
import org.moysha.lab4t.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class AppController {

    private AppService appService;
    private UserService userService;


    @GetMapping("/all-app")
    public List<Application> applicationList(){
        return appService.getApplications();
    }
    @GetMapping("/{id}")
    public Application getApplication(@PathVariable Long id) {
        return appService.getApplication(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        boolean success = userService.registerUser(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("Registration successful");
        } else {
            return ResponseEntity.badRequest().body("Username already exists");
        }
    }

}
