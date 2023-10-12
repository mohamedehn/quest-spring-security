package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            try {
                requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.GET,"/avengers/assemble").hasRole("CHAMPION")
                        .requestMatchers(HttpMethod.GET,"/secret-bases").hasRole("DIRECTOR")
                        .anyRequest().authenticated()
                        .and().formLogin()
                        .and().httpBasic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return http.build();
    };

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                        .username("Steve")
                        .password("motdepasse")
                        .roles("CHAMPION")
                        .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Nick")
                .password("flerken")
                .roles("DIRECTOR")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
