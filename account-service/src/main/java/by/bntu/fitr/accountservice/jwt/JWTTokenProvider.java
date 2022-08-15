package by.bntu.fitr.accountservice.jwt;

import by.bntu.fitr.accountservice.constant.CommonConstant;
import by.bntu.fitr.accountservice.entity.Permission;
import by.bntu.fitr.accountservice.entity.Role;
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

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    @SuppressWarnings("unchecked")
    public Role getRole(String token) {
        return (Role) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get(CommonConstant.ROLE);
    }

    @SuppressWarnings("unchecked")
    public Permission getPermission(String token) {
        return (Permission) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get(CommonConstant.PERMISSIONS);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return claims.getBody().getExpiration().getTime() > new Date().getTime();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JWTAuthenticationException("JWT token is expired or invalid");
        }
    }

}
