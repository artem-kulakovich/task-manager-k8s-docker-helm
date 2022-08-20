package by.bntu.fitr.projectservice.dto.response;

import by.bntu.fitr.projectservice.entity.Project;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkspaceResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "createAt")
    private Date createAt;

    @JsonProperty(value = "projectList")
    private List<ProjectResponseDTO> projectResponseDTOList;
}
