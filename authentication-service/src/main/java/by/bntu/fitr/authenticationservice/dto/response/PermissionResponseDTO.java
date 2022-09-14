package by.bntu.fitr.authenticationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

@ApiModel(description = "class represents permission")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PermissionResponseDTO {
    @ApiModelProperty(notes = "id of permission", position = 0, example = "1")
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(notes = "name of permission", position = 1, example = "READ")
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(notes = "date of permission creation", position = 2, example = "22.01.2022")
    @JsonProperty(value = "createAt")
    private OffsetDateTime createAt;
}
