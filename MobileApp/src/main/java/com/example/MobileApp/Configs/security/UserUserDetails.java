package com.example.MobileApp.Configs.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.MobileApp.Entity.Account;

import lombok.Builder;

@Builder
public class UserUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// initialize account for security
	private Account account;
	
	public UserUserDetails(Account account) {
		this.account = account;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(account.getRole().getRoleName()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return account.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
	    return true; // or add your logic
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true; // or add your logic
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true; // or add your logic
	}

	@Override
	public boolean isEnabled() {
	    return true; // or add your logic
	}
}
