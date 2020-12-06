package com.delectable.userauth.security.jwt;

import java.util.Date;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import com.delectable.userauth.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationInMlliseconds}")
	private Long jwtExpirationMs;

	@Value("${jwt.timeSkewInMlliseconds}")
	private Long jwtTimeSkewMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Long expTime = System.currentTimeMillis() + jwtExpirationMs;

		return Jwts.builder().setSubject((userPrincipal.getUsername()))
				.claim("email", userPrincipal.getEmail())
				.claim("scope",
						userPrincipal.getAuthorities().stream().map(item -> item.getAuthority())
								.collect(Collectors.toList()))
				.claim("iat", new Date().getTime()).claim("exp", new Date(expTime).getTime())
				.signWith(convertStringToKey(jwtSecret)).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(convertStringToKey(jwtSecret))
				.setAllowedClockSkewSeconds(jwtTimeSkewMs / 1000).build().parseClaimsJws(token)
				.getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(convertStringToKey(jwtSecret))
					.setAllowedClockSkewSeconds(jwtTimeSkewMs / 1000).build()
					.parseClaimsJws(authToken);
			return true;
		} catch (SecurityException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	Key convertStringToKey(String secret) {
		SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return key;
	}
}
