package by.bntu.fitr.projectservice.api.jwt;

import by.bntu.fitr.projectservice.api.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return claims.getBody().getExpiration().getTime() > new Date().getTime();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JWTAuthenticationException("JWT token is expired or invalid");
        }
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public String getIssuer(String token) {
        return getClaims(token).getIssuer();
    }

    public Long getUserId(String token) {
        return getClaims(token).get(CommonConstant.USER_ID, Long.class);
    }

    public String getEmail(String token) {
        return getClaims(token).get(CommonConstant.EMAIL, String.class);
    }

    public String getRoleName(String token) {
        return getClaims(token).get(CommonConstant.ROLE_NAME, String.class);
    }

    @SuppressWarnings(value = "unchecked")
    public List<String> getProjectRoleName(String token) {
        return (List<String>) getClaims(token).get(CommonConstant.PROJECT_ROLE_NAME);
    }

    @SuppressWarnings(value = "unchecked")
    public List<String> getRolePermissionsName(String token) {
        return (List<String>) getClaims(token).get(CommonConstant.PERMISSIONS_FOR_ROLE);
    }

    @SuppressWarnings(value = "unchecked")
    public Map<String, List<String>> getProjectRolePermissionsName(String token) {
        return (Map<String, List<String>>) getClaims(token).get(CommonConstant.PERMISSIONS_FOR_PROJECT_ROLE);
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
