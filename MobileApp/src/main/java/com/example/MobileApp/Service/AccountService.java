package com.example.MobileApp.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.MobileApp.Entity.Account;

@Service
public interface AccountService {

	Optional<Account> findByUsername(String username);

}
