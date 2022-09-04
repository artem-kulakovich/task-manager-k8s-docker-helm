package by.bntu.fitr.authenticationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "createAt")
    private OffsetDateTime createAt;

    @JsonProperty(value = "rolePermissions")
    private List<PermissionResponseDTO> permissionResponseDTOList;
}

