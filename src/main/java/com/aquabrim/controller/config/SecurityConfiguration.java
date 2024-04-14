//package com.aquabrim.controller.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static com.aquabrim.controller.enums.Role.USER;
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final AuthenticationProvider authenticationProvider;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests(req ->
//                req.requestMatchers("/api/v1/auth/**")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated()
//        ).sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
//
////        httpSecurity
////                .csrf()
////                .disable()
////                .authorizeHttpRequests()
////                .requestMatchers("/api/v1/auth/**")
////                .permitAll()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
////                .and()
////                .authenticationProvider(authenticationProvider)
////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////        return httpSecurity.build();
//    }
//}
