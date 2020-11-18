package com.example.demo.controller;

import com.example.demo.service.MailService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Token;
import com.example.demo.repo.AppUserRepo;
import com.example.demo.repo.TokenRepo;
import com.example.demo.service.UserService;
import com.example.demo.model.AppUser;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;
    private MailService mailService;

    public UserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

    @RequestMapping("/")
    public String startowa() {
        return "home";
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "hello";
    }

    @GetMapping("/sing-up")
    public String singup(Model model) {
        model.addAttribute("user", new AppUser());
        return "sing-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "sing-up";
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "hello";
    }

    @RequestMapping("/wszyscy")
    public String wszyscy() {
        return "wszyscy";
    }

    @RequestMapping("/ForAdmin")
    public String foradmin() {
        return "ForAdmin";
    }

    @RequestMapping("/ForUSer")
    public String foruser() {
        return "ForUSer";
    }

    @RequestMapping("/send")
    public String sendForm() {
        return "send";
    }

    @RequestMapping("/wyslij")
    public String send(@RequestParam("to") String to, @RequestParam("subject")
            String subject, @RequestParam("text") String text)
    {
        try {
            mailService.sendMail(to, subject, text, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "send";
    }
}
