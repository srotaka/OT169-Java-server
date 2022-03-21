package com.alkemy.ong.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfig {

    /*Restringe solamente el endpoint /organization/public para probarlo*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults()).authorizeRequests( authz -> {
                authz.antMatchers("/organization/public").hasAnyRole("ROLE_USER","ROLE_ADMIN").anyRequest().authenticated();
        });
        return http.build();
    }
}
