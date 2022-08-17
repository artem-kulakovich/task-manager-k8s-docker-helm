package by.bntu.fitr.projectservice.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class JWTTokenFilter extends OncePerRequestFilter {
    private final JWTTokenProvider jwtTokenProvider;
    private final JWTContext jwtContext;

    @Autowired
    public JWTTokenFilter(JWTTokenProvider jwtTokenProvider,
                          JWTContext jwtContext) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtContext = jwtContext;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            token = authorizationHeader.substring(7);
        }


        if (jwtTokenProvider.validateToken(token)) {
            jwtContext.setToken(token);
            jwtContext.setUserName(jwtTokenProvider.getUsername(token));
            jwtContext.setUserId(jwtTokenProvider.getUserId(token));
            jwtContext.setEmail(jwtTokenProvider.getEmail(token));
            jwtContext.setRoleName(jwtTokenProvider.getRoleName(token));
            jwtContext.setProjectRoleName(jwtTokenProvider.getProjectRoleName(token));
            jwtContext.setRolePermissionsName(jwtTokenProvider.getRolePermissionsName(token));
            jwtContext.setProjectRolePermissionsName(jwtTokenProvider.getProjectRolePermissionsName(token));
            jwtContext.setIssuer(jwtTokenProvider.getIssuer(token));
            filterChain.doFilter(request, response);
        } else {
            throw new JWTAuthenticationException("");
        }
    }
}
