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
public class ProjectCreateRequestDTO {
    @JsonProperty(value = "name")
    @Size(
            min = ValidationConstant.Constraint.PROJECT_NAME_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.PROJECT_NAME_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.PROJECT_NAME_VALIDATION_MSG
    )
    @NotNull
    private String name;

    @JsonProperty(value = "description")
    @Size(
            max = ValidationConstant.Constraint.PROJECT_DESCRIPTION_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.PROJECT_DESCRIPTION_VALIDATION_MSG
    )
    private String description;

    @JsonProperty(value = "workspaceId")
    @NotNull
    private Long WorkspaceId;
}
