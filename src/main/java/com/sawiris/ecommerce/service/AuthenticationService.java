package com.sawiris.ecommerce.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sawiris.ecommerce.repository.UserRepo;
import com.sawiris.ecommerce.request.AuthenticationRequest;
import com.sawiris.ecommerce.request.RegisterRequest;
import com.sawiris.ecommerce.response.AuthenticationResponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Service
public class AuthenticationService {

	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final com.sawiris.ecommerce.security.JwtService jwtService;
	private final AuthenticationManager authenticationManager;



	public AuthenticationResponse register(RegisterRequest request)
	{
		var user = com.sawiris.ecommerce.entity.User.builder()
				.firstName(request.getFirstname())
				.lastName(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.phone(request.getPhone())
				.Address(request.getAddress())
				.build();
		userRepo.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
		
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		// if i pass the previous function this mean the authentication is correct 
		
		var user  = userRepo.findUserByEmail(request.getEmail())
				.orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
		
		
	}
}
