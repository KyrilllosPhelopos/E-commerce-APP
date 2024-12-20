package com.sawiris.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticatedFilter jwtFilter;

    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http
			.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/auth/**" , "/swagger-ui/**" , "swagger-ui.html" , "v3/api-docs/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);

			return http.build();
	}

}
