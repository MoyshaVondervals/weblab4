package org.moysha.lab4t.controller;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.Point;
import org.moysha.lab4t.models.User;
import org.moysha.lab4t.services.PointService;
import org.moysha.lab4t.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class AppController {

    private PointService pointService;
    private UserService userService;


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

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        System.out.println("1!!@#$%^%$#@!@#$%^&");
        boolean success = userService.loginUser(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.badRequest().body("Login failed");
    }

    @PostMapping("/addPoint")
    public ResponseEntity<?> addPoint(@RequestBody Point point) {
        System.out.println("Add point");
        pointService.addPoint(point);
        return ResponseEntity.ok("Add point successful");
    }



}
