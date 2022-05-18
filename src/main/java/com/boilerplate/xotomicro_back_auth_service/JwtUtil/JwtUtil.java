// package com.boilerplate.xotomicro_back_auth_service.JwtUtil;

// import io.jsonwebtoken.Claims;

// import io.jsonwebtoken.Jwts;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import java.util.Date;
// import java.util.function.Function;

// @Service
// public class JwtUtil {

//     @Value("${jwt.token.secret-key}")
//     private String secretKey;

//     public Date extractExpiration(String token) {
//         return extractClaim(token, Claims::getExpiration);
//     }

//     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }

//     private Claims extractAllClaims(String token) {
//         return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//     }

//     public Boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }

//     public Boolean validateToken(String token) {
//         return !isTokenExpired(token);
//     }
// }