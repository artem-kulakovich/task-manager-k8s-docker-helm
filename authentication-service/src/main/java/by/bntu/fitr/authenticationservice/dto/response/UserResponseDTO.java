package by.bntu.fitr.authenticationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@ApiModel(description = "class respresents user")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Setter
public class UserResponseDTO {
    @ApiModelProperty(notes = "id of user", position = 0, example = "1")
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(notes = "first name of user", position = 1, example = "Artem")
    @JsonProperty(value = "firstName")
    private String firstName;

    @ApiModelProperty(notes = "last name of user", position = 2, example = "Kulakovich")
    @JsonProperty(value = "lastName")
    private String lastName;

    @ApiModelProperty(notes = "user name of user", position = 3, example = "arku123")
    @JsonProperty(value = "userName")
    private String userName;

    @ApiModelProperty(notes = "email of user", position = 4, example = "artem@gmail.ru")
    @JsonProperty(value = "email")
    private String email;

    @ApiModelProperty(notes = "date of user creation", position = 5, example = "22.01.2022")
    @JsonProperty(value = "createAt")
    private OffsetDateTime createAt;

    @ApiModelProperty(notes = "role id of user", position = 6, example = "1")
    @JsonProperty(value = "roleId")
    private Integer roleId;

    @JsonProperty(value = "role")
    private RoleResponseDTO roleResponseDTO;

}
