package com.example.sourceSafeMaven.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) //csrf is specially token from spring it's important in banking things and when there is a session but in our case we have stateless session so we should do disable
              .authorizeHttpRequests((authReq) -> authReq.requestMatchers("/api/auth/**").permitAll() // .authorizeRequests().antMatchers("/api/**", "/h2-console/**").permitAll()
                      .anyRequest().authenticated()
              );

//        http.headers().frameOptions().disable();
//        httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
