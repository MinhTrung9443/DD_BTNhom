package com.example.MobileApp.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobileApp.Entity.Account;
import com.example.MobileApp.Repository.AccountRepository;
import com.example.MobileApp.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
	
	
}
