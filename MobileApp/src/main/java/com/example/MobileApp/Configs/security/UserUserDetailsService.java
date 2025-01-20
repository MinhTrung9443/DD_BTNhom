package com.example.MobileApp.Configs.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MobileApp.Entity.Account;
import com.example.MobileApp.Repository.AccountRepository;

@Service
public class UserUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Account> acc = accountRepository.findByUsername(username);
		if (acc.isPresent()) {
	        System.out.println("User found: " + acc.get().getUsername());
	        return new UserUserDetails(acc.get());  //tạo user security cho user này
	    }
	    // System.out.println("User not found: " + username);
	    throw new UsernameNotFoundException(username);
	}

}
