package com.example.bookHaven.config;


import com.example.bookHaven.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;


    @Bean
    public UserDetailsService userDetailsSvc() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable().cors().and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/authenticate/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/books/").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers(HttpMethod.PUT, "/api/books/").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers(HttpMethod.DELETE, "/api/books/").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers("/api/books/").permitAll()
                .requestMatchers(HttpMethod.PUT,"/api/customers/").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/customers/").permitAll()
                .requestMatchers("/api/customers/**").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers("/api/reports/revenue-summary").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers("/api/reports/custom-report").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers("/api/reports/custom-report/by-genre").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers("/api/reports/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/transactions/").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/transactions/by-customer/").hasAnyAuthority("ADMIN", "STUFF")
                .requestMatchers(HttpMethod.GET,"/api/transactions/").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsSvc());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
