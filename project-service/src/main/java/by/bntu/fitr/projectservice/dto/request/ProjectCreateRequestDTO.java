package by.bntu.fitr.projectservice.dto.request;

import by.bntu.fitr.projectservice.entity.ProjectInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProjectCreateRequestDTO {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "projectInfoList")
    private List<ProjectInfo> projectInfoList;
}
