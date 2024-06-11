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

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final CustomUserDetailsService userDetailsService;
	private static final String SECRET_KEYY = "miclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecretamiclavesecreta";
	SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEYY));

    private static final SecretKey SECRET_KEY = JwtUtils.getSecretKey();

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		// Skip authentication for login and register mutations
		System.out.println("PASANDO-------------------------------------------------");

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
		System.out.println("ENTR-------------------------------------------------");

		if (token != null) {
			System.out.println("PASANDO-------------------------------------------------");

			token = token.replace("Bearer ", ""); // Remove the Bearer prefix
			Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
			        .build()
			        .parseClaimsJws(token)
			        .getBody();
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
