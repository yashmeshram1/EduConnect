package com.wecp.progressive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wecp.progressive.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //Authentication 
                        .antMatchers("/auth/**").permitAll()
                        
                        // Attendance 
                        .antMatchers("/attendance/**").hasAuthority("TEACHER")
                        .antMatchers(HttpMethod.GET, "/attendance/**").hasAuthority("STUDENT")

                        // Course
                        .antMatchers("/course/**").hasAuthority("TEACHER")
                        .antMatchers(HttpMethod.GET, "/course/**").hasAuthority("STUDENT")

                        // Enrollment
                        .antMatchers("/enrollment/**").hasAuthority("TEACHER")
                        .antMatchers(HttpMethod.GET, "/enrollment/**").hasAuthority("STUDENT")

                        // Student
                        .antMatchers("/student/**").hasAuthority("STUDENT")

                        // Teacher
                        .antMatchers("/teacher/**").hasAuthority("TEACHER")
                        .anyRequest().authenticated()
                    );

        http.addFilterBefore(jwtRequestFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}