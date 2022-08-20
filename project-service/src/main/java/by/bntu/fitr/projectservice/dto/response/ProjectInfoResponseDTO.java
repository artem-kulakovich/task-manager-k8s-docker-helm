package by.bntu.fitr.projectservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectInfoResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "role")
    private RoleResponseDTO roleResponseDTO;
}
