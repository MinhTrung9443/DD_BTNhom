package com.example.MobileApp.Service.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl {
	@Autowired
    private JavaMailSender mailSender;
	
	// store OTPs temporarily (use a database in production) --> update later
    private final Map<String, String> otpStorage = new HashMap<>();
    
    // generate OTP
    public String generateOTP(String email) {
        String otp = String.valueOf(new Random().nextInt(999999 - 100000) + 100000); // 6-digit OTP
        otpStorage.put(email, otp);
        return otp;
    }
    
    // validate OTP
    public boolean validateOTP(String email, String otp) {
        return otp.equals(otpStorage.get(email));
    }

    // send OTP via email
    public void sendOTPEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for password reset is: " + otp);
        message.setFrom("pythonsendmail8@gmail.com");

        mailSender.send(message);
    }

    // remove OTP after validation
    public void removeOTP(String email) {
        otpStorage.remove(email);
    }
}	
