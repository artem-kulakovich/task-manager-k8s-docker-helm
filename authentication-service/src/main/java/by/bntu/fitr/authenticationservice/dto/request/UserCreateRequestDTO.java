package by.bntu.fitr.authenticationservice.dto.request;

import by.bntu.fitr.authenticationservice.constant.ValidationConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreateRequestDTO {
    @JsonProperty(value = "userName")
    @Size(
            min = ValidationConstant.Constraint.USER_NAME_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.USER_NAME_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.USER_NAME_VALIDATION_MSG
    )
    @NotNull
    private String userName;

    @JsonProperty(value = "email")
    @Size(
            min = ValidationConstant.Constraint.EMAIL_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.EMAIL_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.EMAIL_VALIDATION_MSG
    )
    @NotNull
    private String email;

    @JsonProperty(value = "lastName")
    @Size(
            min = ValidationConstant.Constraint.LAST_NAME_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.LAST_NAME_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.LAST_NAME_VALIDATION_MSG
    )
    @NotNull
    private String lastName;

    @JsonProperty(value = "firstName")
    @Size(
            min = ValidationConstant.Constraint.FIRST_NAME_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.FIRST_NAME_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.USER_NAME_VALIDATION_MSG
    )
    @NotNull
    private String firstName;

    @JsonProperty(value = "password")
    @Size(
            min = ValidationConstant.Constraint.PASSWORD_MIN_CONSTRAINT,
            max = ValidationConstant.Constraint.PASSWORD_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.PASSWORD_VALIDATION_MSG
    )
    @NotNull
    private String password;

    @JsonProperty(value = "repeatPassword")
    @NotNull
    private String repeatPassword;
}
