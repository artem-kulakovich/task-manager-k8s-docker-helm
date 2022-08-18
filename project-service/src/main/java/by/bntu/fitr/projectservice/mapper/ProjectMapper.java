package by.bntu.fitr.projectservice.mapper;

import by.bntu.fitr.projectservice.dto.response.ProjectInfoResponseDTO;
import by.bntu.fitr.projectservice.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
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
        return new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                projectInfoMapper.toProjectInfosResponseDTO(
                        getProjectInfoListWithoutNull(project)
                )
        );
    }

    public List<ProjectResponseDTO> toProjectResponseDTOList(List<Project> projectList) {
        return projectList.stream().map(project -> new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                projectInfoMapper.toProjectInfosResponseDTO(getProjectInfoListWithoutNull(project))
        )).collect(Collectors.toList());
    }

    private List<ProjectInfo> getProjectInfoListWithoutNull(Project project) {
        return project.getProjectInfoList() == null ? Collections.emptyList() : project.getProjectInfoList();
    }
}
