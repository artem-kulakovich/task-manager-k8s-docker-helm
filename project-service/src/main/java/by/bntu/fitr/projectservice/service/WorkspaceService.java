package by.bntu.fitr.projectservice.service;

import by.bntu.fitr.projectservice.dto.request.WorkspaceCreateRequestDTO;
import by.bntu.fitr.projectservice.entity.Workspace;

import java.util.List;

public interface WorkspaceService {
    Workspace createWorkspace(final WorkspaceCreateRequestDTO workspaceCreateRequestDTO);

    Workspace getWorkspaceById(Long id);

    List<Workspace> getAllWorkspacesBelongedToCurrentUser();
}
