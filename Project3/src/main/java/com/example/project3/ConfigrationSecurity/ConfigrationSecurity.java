package com.example.project3.ConfigrationSecurity;

import com.example.project3.Service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigrationSecurity {
private final MyUserDetailsService myUserDetailsService;

@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }


    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    //     http.csrf().disable() // Disable CSRF for testing purposes
    //             .authorizeHttpRequests()
    //             .anyRequest().permitAll() // Allow all requests
    //             .and()
    //             .logout().disable() // Disable logout endpoint
    //             .httpBasic().disable(); // Disable HTTP Basic Authentication

    //     return http.build();
    // }


 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/customer/register").permitAll()
                .requestMatchers("/api/v1/employee/register").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .httpBasic();

        return httpSecurity.build();
    }


