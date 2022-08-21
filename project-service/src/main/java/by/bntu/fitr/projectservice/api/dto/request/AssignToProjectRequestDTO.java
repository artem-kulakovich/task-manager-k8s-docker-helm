package by.bntu.fitr.projectservice.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AssignToProjectRequestDTO {
    @JsonProperty(value = "projectId")
    private Long projectId;

    @JsonProperty(value = "roleId")
    private Long roleId;

    @JsonProperty(value = "email")
    private String email;

}
