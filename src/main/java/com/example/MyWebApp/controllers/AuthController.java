package com.example.MyWebApp.controllers;

import com.example.MyWebApp.models.Client;
import com.example.MyWebApp.models.Sotrudnik;
import com.example.MyWebApp.services.ClientService;
import com.example.MyWebApp.services.SotrudnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AuthController {
    @Autowired
    ClientService clientService;
    @Autowired
    SotrudnikService sotrudnikService;

    @GetMapping("/login")
    public String authorization() {
        return "login";
    }

    @PostMapping("/register")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String passwordConfirm, Model model) {
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "login";
        }
        for (Client client : clientService.findAll()) {
            if (client.getEmail().equals(username)) {
                model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                return "login";
            }
        }
        for (Sotrudnik sotrudnik : sotrudnikService.findAll()) {
            if (sotrudnik.getEmail().equals(username)) {
                model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
                return "login";
            }
        }
        Client client = new Client(username, password);
        clientService.saveClient(client);
        return "redirect:/login";
    }
}
