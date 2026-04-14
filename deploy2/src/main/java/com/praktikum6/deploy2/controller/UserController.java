package com.praktikum6.deploy2.controller;

import com.praktikum6.deploy2.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140020";

    private List<User> userList = new ArrayList<>();


    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login (@RequestParam String username,
                         @RequestParam String password,
                         Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/home")
    public String homepage(Model model){
        model.addAttribute("users", userList);
        return "home";
    }

    @GetMapping("/input-data")
    public String tampilkanForm() {
        return "form";
    }

    @PostMapping("/submit-data")
    public String prosesDataMahasiswa(@RequestParam String nama,
                                      @RequestParam String nim,
                                      @RequestParam("jenis_kelamin") String jenisKelamin) {
        User newUser = new User(nama, nim, jenisKelamin);

        userList.add(newUser);

        return "redirect:/home";
    }
}

