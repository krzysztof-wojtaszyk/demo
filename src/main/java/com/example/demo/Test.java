package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Test {
    private AppUserRepo appUserRepo;

    public Test(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {

        AppUser appUser = new AppUser();

        appUser.setUsername("Admin");
        appUser.setPassword(passwordEncoder.encode("admin"));
        appUser.setRole("ROLE_ADMIN");
        appUserRepo.save(appUser);

        AppUser appUser1 = new AppUser();

        appUser1.setUsername("User");
        appUser1.setPassword(passwordEncoder.encode("123"));
        appUser1.setRole("ROLE_USER");
        appUserRepo.save(appUser1);


    }


}
