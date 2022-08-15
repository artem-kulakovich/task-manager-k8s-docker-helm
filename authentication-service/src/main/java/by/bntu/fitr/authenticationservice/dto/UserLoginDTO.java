package by.bntu.fitr.authenticationservice.dto;

import by.bntu.fitr.authenticationservice.constant.ValidationConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserLoginDTO {
    @JsonProperty(value = "userName")
    @Size(
            min = ValidationConstant.Constraint.USER_NAME_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.USER_NAME_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.USER_NAME_VALIDATION_MSG
    )
    private String userName;

    @JsonProperty(value = "password")
    @Size(
            min = ValidationConstant.Constraint.PASSWORD_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.PASSWORD_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.PASSWORD_VALIDATION_MSG
    )
    private String password;
}
