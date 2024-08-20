package com.techxtor.springbootsecuritylearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                // To have new session every time
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * //    -----------NoOpPasswordEncoder
     *
     * @Bean public InMemoryUserDetailsManager userDetailsService() {
     * UserDetails user = User.withUsername("spring")
     * .password("{noop}secret")
     * .roles("USER")
     * .build();
     * return new InMemoryUserDetailsManager(user);
     * }
     **/

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user1 = User
                .withUsername("manish")
                .password(encoder.encode("pass1234"))
                .build();
        UserDetails user2 = User
                .withUsername("shruti")
                .password(encoder.encode("pass12345"))
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
