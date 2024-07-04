package com.nk.Jwt_Impl.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nk.Jwt_Impl.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUsernameFromJwtToken(String token) {
		String username = null;
		try {
			username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT Signature : " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println("Malformed JWT Token : " + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token is expired : " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("JWT Token is not supported : " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Generic Exception : " + e.getMessage());
		}
		return false;
	}

}
