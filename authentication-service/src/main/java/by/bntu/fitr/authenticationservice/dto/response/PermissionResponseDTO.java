package by.bntu.fitr.authenticationservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionResponseDTO {
    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "name")
    private String name;
}
