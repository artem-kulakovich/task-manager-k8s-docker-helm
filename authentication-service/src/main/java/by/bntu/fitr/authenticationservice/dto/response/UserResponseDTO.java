package by.bntu.fitr.authenticationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Setter
public class UserResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "createAt")
    private OffsetDateTime createAt;

    @JsonProperty(value = "roleId")
    private Integer roleId;

    @JsonProperty(value = "role")
    private RoleResponseDTO roleResponseDTO;

}
