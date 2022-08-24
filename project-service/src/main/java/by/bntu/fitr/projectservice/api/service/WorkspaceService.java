package by.bntu.fitr.projectservice.api.service;

import by.bntu.fitr.projectservice.api.dto.request.WorkspaceCreateRequestDTO;
import by.bntu.fitr.projectservice.api.entity.Workspace;

import java.util.List;

public interface WorkspaceService {
    Workspace createWorkspace(final WorkspaceCreateRequestDTO workspaceCreateRequestDTO);

    Workspace getWorkspaceById(final Long id);

    List<Workspace> getAllWorkspacesBelongedToCurrentUser();

    boolean isWorkspaceExists(final String name, final Long userId);
}
