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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public String createToken(long userId, String userName, String email,
                              String roleName,
                              List<String> rolePermissionsName) {
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put(CommonConstant.EMAIL, email);
        claims.put(CommonConstant.USER_ID, userId);
        claims.put(CommonConstant.ROLE_NAME, roleName);
        claims.put(CommonConstant.PERMISSIONS_FOR_ROLE, rolePermissionsName);

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
