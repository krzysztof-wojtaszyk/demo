package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Controler {

    private UserService userService;

    public Controler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/foradmin")
    public String foradmin() {
        return "ForAdmin";
    }

    @GetMapping("/foruser")
    public String foruser() {
        return "ForUSer";
    }

    @GetMapping("/forall")
    public String forall() {
        return "all";
    }

    @GetMapping("/")
    public String wszyscy() {
        return "wszyscy";
    }


    @GetMapping("/jakchcesz")
    public String jakchcesz(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/rejestracja")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "register";
    }
}
