package by.bntu.fitr.projectservice.mapper;

import by.bntu.fitr.projectservice.dto.response.ProjectInfoResponseDTO;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectInfoMapper {
    private final RoleMapper roleMapper;

    @Autowired
    public ProjectInfoMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public List<ProjectInfoResponseDTO> toProjectInfosResponseDTO(List<ProjectInfo> projectInfos) {
        return projectInfos.stream().map(projectInfo -> new ProjectInfoResponseDTO(
                projectInfo.getId(),
                projectInfo.getUserId(),
                roleMapper.toRoleResponseDTO(projectInfo.getRole())
        )).collect(Collectors.toList());
    }
}