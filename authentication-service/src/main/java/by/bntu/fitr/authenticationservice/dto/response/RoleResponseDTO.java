package by.bntu.fitr.authenticationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RoleResponseDTO {
    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "rolePermissions")
    private List<PermissionResponseDTO> permissionResponseDTOList;
}

