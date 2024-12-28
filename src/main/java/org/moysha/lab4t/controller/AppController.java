package org.moysha.lab4t.controller;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.Application;
import org.moysha.lab4t.services.AppService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AppController {
    private AppService appService;
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome.html";
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

}
