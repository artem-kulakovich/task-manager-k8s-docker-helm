package by.bntu.fitr.projectservice.api.mapper;

import by.bntu.fitr.projectservice.api.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.api.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {
    private final ProjectInfoMapper projectInfoMapper;

    @Autowired
    public ProjectMapper(ProjectInfoMapper projectInfoMapper) {
        this.projectInfoMapper = projectInfoMapper;
    }

    public ProjectResponseDTO toProjectResponseDTO(Project project) {
        return project == null
                ? null
                : new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getCreateAt(),
                projectInfoMapper.toProjectInfoResponseDTOList(project.getProjectInfoList())
        );
    }

    public List<ProjectResponseDTO> toProjectResponseDTOList(List<Project> projectList) {
        return projectList == null
                ? null
                : projectList.stream().map(this::toProjectResponseDTO)
                .collect(Collectors.toList());
    }
}
