package com.example.MobileApp.Controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MobileApp.Configs.auth.AuthenticationRequest;
import com.example.MobileApp.Configs.auth.AuthenticationResponse;
import com.example.MobileApp.Configs.auth.AuthenticationService;
import com.example.MobileApp.Configs.auth.RegisterRequest;
import com.example.MobileApp.Configs.auth.RegisterResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/request-register")
	public ResponseEntity<RegisterResponse> requestRegister(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.requestRegister(request));
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterResponse response) {
		return ResponseEntity.ok(authService.register(response));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}
}
