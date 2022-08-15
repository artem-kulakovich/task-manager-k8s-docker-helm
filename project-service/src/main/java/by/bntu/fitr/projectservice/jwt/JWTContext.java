package by.bntu.fitr.projectservice.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class JWTContext {
    private String token;
    private String userName;
    private long userId;
    private String email;

}
