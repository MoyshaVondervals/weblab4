package org.moysha.lab4t.controller;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.Application;
import org.moysha.lab4t.services.AppService;
import org.moysha.lab4t.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AppController {

    private AppService appService;
    private UserService userService;
    @GetMapping("/")
    public String startPage() {
        return "welcome"; // Название шаблона стартовой страницы
    }

    @GetMapping("/startPage")
    public String startPage(Model model) {
        return "startPage";
    }

    @GetMapping("/all-app")
    public List<Application> applicationList(){
        return appService.getApplications();
    }
    @GetMapping("/{id}")
    public Application getApplication(@PathVariable Long id) {
        return appService.getApplication(id);
    }
    @GetMapping("/login")
    public String loginPage(Model model, String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        return "login"; // Название вашего HTML-шаблона в папке templates
    }
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Отображаем страницу регистрации
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, Model model) {
        System.out.println(username+ "  "+ password);
        boolean success = userService.registerUser(username, password);
        if (success) {
            model.addAttribute("message", "Registration successful! Please log in.");
            return "login"; // Перенаправляем на страницу логина
        } else {
            model.addAttribute("errorMessage", "Username already exists.");
            return "register"; // Возвращаемся на страницу регистрации
        }
    }
}
