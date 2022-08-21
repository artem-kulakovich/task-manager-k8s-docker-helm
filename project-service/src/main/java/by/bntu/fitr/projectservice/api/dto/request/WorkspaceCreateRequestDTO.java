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
public class WorkspaceCreateRequestDTO {
    @JsonProperty(value = "name")
    @Size(
            min = ValidationConstant.Constraint.WORKSPACE_NAME_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.WORKSPACE_NAME_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.WORKSPACE_NAME_VALIDATION_MSG
    )
    @NotNull
    private String name;
}
