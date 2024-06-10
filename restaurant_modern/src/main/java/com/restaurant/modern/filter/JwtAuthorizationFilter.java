package com.restaurant.modern.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final UserDetailsService userDetailsService;
	private static final String SECRET_KEY = "miclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecreta";
	SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			token = token.replace("Bearer ", ""); // Remove the Bearer prefix
			Claims claims = Jwts.parser()
			        .verifyWith(secret)
			        .build()
			        .parseSignedClaims(token)
			        .getPayload();
			String username = claims.getSubject();
			List<String> roles = claims.get("roles", List.class);

			if (username != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				return new UsernamePasswordAuthenticationToken(userDetails, null,
						roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			}
		}
		return null;
	}
}
