package com.example.saless.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@SuppressWarnings("removal")
public class WebSecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/add").permitAll()
                        .requestMatchers(HttpMethod.POST, "/add").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout().logoutSuccessUrl("/").permitAll();

        return http.build();
    }
}