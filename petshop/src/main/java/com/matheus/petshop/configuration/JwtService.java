package com.matheus.petshop.configuration;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String chave_secreta = "46294A404E635266556A586E327234753778214125442A472D4B615064536756";
	
	public String getCpf(String token) {
		return extractClaim(token,Claims::getSubject);	
	}
	
	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	private String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder()
			   .setClaims(extraClaims)
			   .setSubject(userDetails.getUsername())
			   .setIssuedAt(new Date(System.currentTimeMillis()))
			   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
			   .signWith(getSignInKey(), SignatureAlgorithm.HS256)
			   .compact();
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String cpf = getCpf(token);
		return (cpf.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}


	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(chave_secreta);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
