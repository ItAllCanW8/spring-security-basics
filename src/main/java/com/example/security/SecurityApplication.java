package com.example.security;

import com.example.security.domain.AppUser;
import com.example.security.domain.Role;
import com.example.security.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AppUserService userService){
        return args -> {
            userService.saveRole(Role.builder().name("USER").build());
            userService.saveRole(Role.builder().name("ADMIN").build());
            userService.saveRole(Role.builder().name("MANAGER").build());
            userService.saveRole(Role.builder().name("SUPER_ADMIN").build());

            userService.saveUser(AppUser.builder()
                    .name("User 1")
                    .username("John")
                    .password("1234")
                    .build());
            userService.saveUser(AppUser.builder()
                    .name("User 2")
                    .username("Jacob")
                    .password("1234")
                    .build());
            userService.saveUser(AppUser.builder()
                    .name("User 3")
                    .username("Georgy")
                    .password("1234")
                    .build());
            userService.saveUser(AppUser.builder()
                    .name("User 4")
                    .username("George")
                    .password("1234")
                    .build());

            userService.addRoleToUser("John", "USER");
            userService.addRoleToUser("Jacob", "SUPER_ADMIN");
            userService.addRoleToUser("Jacob", "USER");
//            userService.addRoleToUser("Georgy", "ADMIN");
//            userService.addRoleToUser("Georgy", "USER");
            userService.addRoleToUser("George", "MANAGER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
