package kg.mega.internet_store_v1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String signKey;
    @Value("${jwt.token-lifetime}")
    private Duration tokenLifetime;

    public String generateToken(UserDetails user) {
        Date issusedDate = new Date();
        Date expirationDate = new Date(issusedDate.getTime()+tokenLifetime.toMillis());
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        claims.put("username", user.getUsername());
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(issusedDate)
                .expiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,signKey)
                .compact();
    }

    public AuthResponseDto getTokenAndData(String token){
        AuthResponseDto authResponseDto = new AuthResponseDto();
        Claims claims = getClaimsFromToken(token);
        authResponseDto.setUsername(claims.getSubject());
        authResponseDto.setToken(token);
        authResponseDto.setExpires(claims.getExpiration());
        return authResponseDto;
    }
    private Claims getClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(signKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public  List<String> getRoles(String token) {
        return getClaimsFromToken(token).get("roles", List.class);
    }
   }
