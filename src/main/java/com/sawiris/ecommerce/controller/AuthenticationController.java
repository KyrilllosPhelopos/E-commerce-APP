package com.sawiris.ecommerce.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sawiris.ecommerce.request.AuthenticationRequest;
import com.sawiris.ecommerce.request.RegisterRequest;
import com.sawiris.ecommerce.response.AuthenticationResponse;
import com.sawiris.ecommerce.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<?> register(
			@RequestBody @Valid RegisterRequest request , BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			StringBuilder errors = new StringBuilder();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errors.append(error.getDefaultMessage()).append("\n");
			}
			return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
		}


			return ResponseEntity.ok(service.register(request));
		}


		@PostMapping("/authenticate")
		public ResponseEntity<AuthenticationResponse> authenticate(
				@RequestBody AuthenticationRequest request
				) {
			return ResponseEntity.ok(service.authenticate(request));
		}


	}