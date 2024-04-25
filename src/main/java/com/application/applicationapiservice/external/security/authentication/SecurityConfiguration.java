package com.application.applicationapiservice.external.security.authentication;

import com.application.applicationapiservice.common.enums.UserType;
import com.application.applicationapiservice.services.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final IUserService userService;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(final IUserService userService,
                                 final JWTAuthenticationFilter jwtAuthenticationFilter,
                                 final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/**")
                        .permitAll()
//                        .requestMatchers("/api/v1/**")
//                        .permitAll()
                        .requestMatchers("/api/v1/patients/**")
                        .hasAnyAuthority(UserType.ADMIN.name(), UserType.DONATION_CENTER.name(), UserType.PHILANTHROPIST.getValue())
                        .requestMatchers("/api/v1/donations/**")
                        .hasAnyAuthority(UserType.ADMIN.name(), UserType.DONATION_CENTER.name(), UserType.PHILANTHROPIST.getValue())
                        .requestMatchers("/api/v1/reports/**")
                        .hasAnyAuthority(UserType.ADMIN.name(), UserType.DONATION_CENTER.name(), UserType.PHILANTHROPIST.getValue())
                        .requestMatchers("/api/v1/philanthropists/**")
                        .hasAnyAuthority(UserType.ADMIN.name(), UserType.DONATION_CENTER.name(), UserType.PHILANTHROPIST.getValue())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
