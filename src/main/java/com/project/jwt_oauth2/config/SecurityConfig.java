package com.project.jwt_oauth2.config;

import com.project.jwt_oauth2.config.userConfig.UserInfoConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*5. This is the fifth config bean created to access the user related API
     during authentication calls are made it comes first to this bean  */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final UserInfoConfig userInfo;

    //This is the very first method in order after the user related API call is made.
    // SecurityFilterChain() first filters the url starting with "/api/**" and
    // then secondly should check for csrf(disabled) and thirdly authenticate any requests having UserDetailsService
    // and fourthly follows basic http protocols (if defined then custom or else default protocols) and then build.
    @Order(1)
    @Bean
    public SecurityFilterChain userApiSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher("/api/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .userDetailsService(userInfo)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

}
