package by.bntu.fitr.notificationservice.dto;

import by.bntu.fitr.notificationservice.constant.ValidationConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleEmailSendRequestDTO {
    @JsonProperty(value = "sendTo")
    @NotNull
    private String sendTo;

    @JsonProperty(value = "msg")
    @Size(
            max = ValidationConstant.ValidationConstraint.MESSAGE_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.MESSAGE_VALIDATION_MSG
    )
    @NotNull
    private String msg;

    @JsonProperty(value = "subject")
    @Size(
            max = ValidationConstant.ValidationConstraint.SUBJECT_MAX_CONSTRAINT,
            message = ValidationConstant.ValidationMsg.SUBJECT_VALIDATION_MSG
    )
    @NotNull
    private String subject;
}
