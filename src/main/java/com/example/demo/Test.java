package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.model.AppUser;
import com.example.demo.repo.AppUserRepo;

@Configuration
public class Test {

    public Test(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        AppUser appUser = new AppUser();
        appUser.setUsername("Admin");
        appUser.setPassword(passwordEncoder.encode("Admin"));
        appUser.setRole("ROLE_ADMIN");
        appUser.setEnabled(true);


        AppUser appUser2 = new AppUser();
        appUser2.setUsername("User");
        appUser2.setPassword(passwordEncoder.encode("User"));
        appUser2.setRole("ROLE_USER");
        appUser2.setEnabled(true);

        appUserRepo.save(appUser);
        appUserRepo.save(appUser2);



    }

}
