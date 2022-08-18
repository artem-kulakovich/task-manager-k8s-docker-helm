package by.bntu.fitr.authenticationservice.dto.response;

import by.bntu.fitr.authenticationservice.entity.Permission;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectRoleResponseDTO {
    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "projectRolePermissions")
    private List<Permission> permissionList;
}
