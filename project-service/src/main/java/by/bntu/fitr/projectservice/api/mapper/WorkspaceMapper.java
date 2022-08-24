package by.bntu.fitr.projectservice.api.mapper;

import by.bntu.fitr.projectservice.api.dto.response.WorkspaceResponseDTO;
import by.bntu.fitr.projectservice.api.entity.Workspace;
import by.bntu.fitr.projectservice.api.dto.request.WorkspaceCreateRequestDTO;
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

    public Workspace toWorkSpace(final WorkspaceCreateRequestDTO workspaceCreateRequestDTO) {
        return new Workspace(
                workspaceCreateRequestDTO.getName()
        );
    }

    public WorkspaceResponseDTO toWorkspaceResponseDTO(final Workspace workspace) {
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

    public List<WorkspaceResponseDTO> toWorkspaceResponseDTOList(final List<Workspace> workspaceList) {
        return workspaceList == null
                ? null
                : workspaceList.stream().map(this::toWorkspaceResponseDTO)
                .collect(Collectors.toList());
    }
}
