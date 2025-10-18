//package com.projects.myauthapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
////security:
////user:
////name: admin
////password: admin123
////roles: ADMIN
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
////        csrf ko disable karo
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(
//                auth -> auth
//                        .requestMatchers("/actuator/**", "/public/**").permitAll()
//                        .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//        );
//        http.httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//}
