package by.bntu.fitr.projectservice.api.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTContext {
    private String token;
    private String userName;
    private Long userId;
    private String email;
    private String roleName;
    private List<String> projectRoleName;
    private List<String> rolePermissionsName;
    private Map<String, List<String>> projectRolePermissionsName;
    private String issuer;

}
