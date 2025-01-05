package org.moysha.lab4t.controller;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.Point;
import org.moysha.lab4t.models.User;
import org.moysha.lab4t.services.PointService;
import org.moysha.lab4t.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class AppController {

    private PointService pointService;
    private UserService userService;
    @GetMapping("/check")
    public boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }
    @GetMapping(value = {"/", "/login", "/dashboard", "/register"})
    public String forwardToReact() {
        return "forward:/index.html"; // Передаём запрос на React
    }

//    @GetMapping("/all-app")
//    public List<Application> applicationList(){
//        return appService.getApplications();
//    }
//    @GetMapping("/{id}")
//    public Application getApplication(@PathVariable Long id) {
//        return appService.getApplication(id);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println("registration activated");
        boolean success = userService.registerUser(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("Registration successful");
        } else {
            return ResponseEntity.badRequest().body("Username already exists");
        }
    }



    @PostMapping("/addPoint")
    public ResponseEntity<?> addPoint() {
        System.out.println("Add point");
//        pointService.addPoint(point);
        return ResponseEntity.ok("Add point successful");
    }



}
