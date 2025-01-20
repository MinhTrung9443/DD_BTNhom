package com.example.MobileApp.Configs.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MobileApp.Configs.jwt.JwtService;
import com.example.MobileApp.Configs.security.UserUserDetails;
import com.example.MobileApp.Entity.Account;
import com.example.MobileApp.Repository.AccountRepository;
import com.example.MobileApp.Repository.RoleRepository;
import com.example.MobileApp.Service.Impl.OTPServiceImpl;

@Service
public class AuthenticationService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private RoleRepository roleReposotory;
	@Autowired 
	private OTPServiceImpl otpService;
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword())
				);
		
		var user = accountRepository.findByUsername(request.getUsername())
								.orElseThrow();
		
		var jwtToken = jwtService.generateToken(new UserUserDetails(user));
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	// register -> send otp through email -> check otp -> failed/success
	public AuthenticationResponse register(RegisterResponse response) {
		// TODO Auto-generated method stub
		
		//check valid otp
		 // Validate OTP and password matching
	    if (!otpService.validateOTP(response.getEmail(), response.getOtpCode())) {
	        //return ResponseEntity.badRequest().body(response);
	    	System.out.println("false otp");
	    
	    }
	
        var role = roleReposotory.findById(1); //1: user
		var user = Account.builder()
				.username(response.getUsername())
				.password(encoder.encode(response.getPassword()))
				.role(role.get())
				.email(response.getEmail())
				.build();
		
		accountRepository.save(user);
		
		var jwtToken = jwtService.generateToken(new UserUserDetails(user));
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	// request register
	public RegisterResponse requestRegister(RegisterRequest req) {
		String otp = otpService.generateOTP(req.getEmail());
        otpService.sendOTPEmail(req.getEmail(), otp);

        return RegisterResponse.builder()
        		.email(req.getEmail())
        		.password(req.getPassword())
        		.username(req.getUsername())
        		.otpCode(otp)
        		.build();
	}
	
	// reset pass
}
