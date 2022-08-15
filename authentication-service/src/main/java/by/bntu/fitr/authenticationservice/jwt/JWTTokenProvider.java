package by.bntu.fitr.authenticationservice.jwt;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@PropertySource("classpath:application.properties")
@NoArgsConstructor
public class JWTTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, String email, long userId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(CommonConstant.EMAIL, email);
        claims.put(CommonConstant.USER_NAME, username);
        claims.put(CommonConstant.USER_ID, userId);
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(CommonConstant.TASK_MANAGER_AUTH_SERVICE)
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
