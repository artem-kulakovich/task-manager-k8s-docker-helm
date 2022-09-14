package by.bntu.fitr.authenticationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@ApiModel(description = "class represents role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleResponseDTO {
    @ApiModelProperty(notes = "id of role", position = 0, example = "1")
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(notes = "name of role", position = 1, example = "USER")
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(notes = "date of role creation", position = 2, example = "22.01.2022")
    @JsonProperty(value = "createAt")
    private OffsetDateTime createAt;

    @JsonProperty(value = "rolePermissions")
    private List<PermissionResponseDTO> permissionResponseDTOList;
}

