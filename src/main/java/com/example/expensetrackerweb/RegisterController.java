package com.example.expensetrackerweb;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final PasswordEncoder passwordEncoder;

    public RegisterController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        String hashed = passwordEncoder.encode(password);
        boolean created = Database.createUser(username, hashed);
        if (!created) {
            model.addAttribute("error", "This username is already taken.");
            return "register";
        }
        return "redirect:/login";
    }
}