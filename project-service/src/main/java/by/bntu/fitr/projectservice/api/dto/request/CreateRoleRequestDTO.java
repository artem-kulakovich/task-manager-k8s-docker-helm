package by.bntu.fitr.projectservice.api.dto.request;

import by.bntu.fitr.projectservice.api.constant.ValidationConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateRoleRequestDTO {
    @JsonProperty(value = "name")
    @Size(
            min = ValidationConstant.Constraint.ROLE_NAME_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.ROLE_NAME_PROJECT_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.ROLE_NAME_VALIDATION_MSG
    )
    @NotNull
    private String name;

    @JsonProperty(value = "projectId")
    @NotNull
    private Long projectId;
}
