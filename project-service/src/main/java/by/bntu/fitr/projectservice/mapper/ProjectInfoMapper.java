package by.bntu.fitr.projectservice.mapper;

import by.bntu.fitr.projectservice.dto.response.ProjectInfoResponseDTO;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectInfoMapper {

    public List<ProjectInfoResponseDTO> toProjectInfosResponseDTO(List<ProjectInfo> projectInfos) {
        return projectInfos.stream().map(projectInfo -> new ProjectInfoResponseDTO(
                projectInfo.getId(),
                projectInfo.getUserId()
        )).collect(Collectors.toList());
    }
}
