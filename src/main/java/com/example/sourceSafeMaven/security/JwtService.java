package com.example.sourceSafeMaven.security;

import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "97e74ee81f5206429721abf0cd87b2450299e2ba3be8feca9d85d3c2c18842e7";

    @Autowired
    private UserRepository userRepository;

    public String getUserName(String token){ //when we will use this method? when we receive the token from the client when client makes a request to the server and through this token we get the user name
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }

//    public String getId(String token){ //when we will use this method? when we receive the token from the client when client makes a request to the server and through this token we get the user name
//        String claims = Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody().getId();
//        return claims;
//    }

    //sami test start
    // Extracts the user ID from the JWT
    public String extractUserId(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        // Assuming your user ID is stored in a claim named "userId"
        return claims.get("id", String.class);
    }

    // Extracts the User object from the JWT
    public User extractUser(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        // Assuming your user ID is stored in a claim named "userId"
        String userId = claims.get("userId", String.class);

        // Create a User object using the extracted user ID
        User user = new User();
        user.setId(Long.parseLong(userId)); // Assuming the user ID is a Long

        return user;
    }


    public Long getUserIdByToken(HttpHeaders headers){
        String token = headers.get("Authorization").get(0);
        String jwt = token.replace("Bearer", "");
        String userEmail = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt)
                .getBody()
                .getSubject()
                ;
        Optional<User> user = userRepository.findByEmail(userEmail);
        Long userId = user.get().getId();
        System.out.println(user.get().getId());
//        System.out.println("hereee" + userId);
        return userId;
    }
    //sami test end

    public String extractUsername(String token) {
        //the subject should be my email or my username
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user){
        Map<String, Object> claims = Map.of(
                "id", user.getId()
        );
        return generateToken(claims, user);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 43200000)) // 24 hours.
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !istokenExpired(token);
    }

    private boolean istokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    private Key key(){
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
//    }
}
