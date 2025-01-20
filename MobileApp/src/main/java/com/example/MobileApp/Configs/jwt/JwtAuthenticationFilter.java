package com.example.MobileApp.Configs.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.MobileApp.Configs.security.UserUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader("Authorization");
		String jwt;
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		
		String username = jwtService.extractUsername(jwt);// extract from jwt token
	
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(jwtService.isTokenValid(jwt, userDetails)) {
				// update security context
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities());
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
