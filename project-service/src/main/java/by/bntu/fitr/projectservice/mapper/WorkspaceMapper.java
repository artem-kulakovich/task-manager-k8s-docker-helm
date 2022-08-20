package by.bntu.fitr.projectservice.mapper;

import by.bntu.fitr.projectservice.dto.request.WorkspaceCreateRequestDTO;
import by.bntu.fitr.projectservice.dto.response.WorkspaceResponseDTO;
import by.bntu.fitr.projectservice.entity.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkspaceMapper {
    private final ProjectMapper projectMapper;

    @Autowired
    public WorkspaceMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public Workspace toWorkSpace(WorkspaceCreateRequestDTO workspaceCreateRequestDTO) {
        return new Workspace(
                workspaceCreateRequestDTO.getName()
        );
    }

    public WorkspaceResponseDTO toWorkspaceResponseDTO(Workspace workspace) {
        return workspace == null
                ? null
                : new WorkspaceResponseDTO(
                workspace.getId(),
                workspace.getName(),
                workspace.getUserId(),
                workspace.getCreateAt(),
                projectMapper.toProjectResponseDTOList(workspace.getProjectList())
        );
    }

    public List<WorkspaceResponseDTO> toWorkspaceResponseDTOList(List<Workspace> workspaceList) {
        return workspaceList == null
                ? null
                : workspaceList.stream().map(this::toWorkspaceResponseDTO)
                .collect(Collectors.toList());
    }
}
