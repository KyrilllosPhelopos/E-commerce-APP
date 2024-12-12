package com.sawiris.ecommerce.security;

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

	private static final String secretKey = "c9d52cf9d822a9f14655db8f5023cec58a23f798c151b41262614b67e96fc1be";

	String extractUsername(String token)
	{
		return extractClaim(token , Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
	    final Claims claims = extractAllClaims(token);
	    return claimsResolver.apply(claims);
	}


	private Claims extractAllClaims(String token)
	{
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();

	}

	private Key getSignInKey()
	{
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
	    return Keys.hmacShaKeyFor(keyBytes);

	}



	//================= generate token methods  ==================

	  private String buildToken(
	          Map<String, Object> extraClaims,
	          UserDetails userDetails,
	          long expiration
	  ) {
	    return Jwts
	            .builder()
	            .setClaims(extraClaims)
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + expiration))
	            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	            .compact();
	  }

	  public String generateToken(UserDetails userDetails) {
		    return generateToken(new HashMap<>(), userDetails);
		  }

		  public String generateToken(
		      Map<String, Object> extraClaims,
		      UserDetails userDetails
		  ) {
		    return buildToken(extraClaims, userDetails, System.currentTimeMillis() +  (24 * 60 * 60 * 1000));
		  }

		  public String generateRefreshToken(
		      UserDetails userDetails
		  ) {
		    return buildToken(new HashMap<>(), userDetails, System.currentTimeMillis() +  (24 * 60 * 60 * 1000));
		  }


		  //====================================

		  public boolean isTokenValid(String token, UserDetails userDetails) {
			    final String username = extractUsername(token);
			    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
			  }

			  private boolean isTokenExpired(String token) {
			    return extractExpiration(token).before(new Date());
			  }

			  private Date extractExpiration(String token) {
			    return extractClaim(token, Claims::getExpiration);
			  }
}
