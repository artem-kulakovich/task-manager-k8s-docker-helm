package by.bntu.fitr.projectservice.api.service;

import by.bntu.fitr.projectservice.api.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.api.dto.request.AssignToProjectRequestDTO;
import by.bntu.fitr.projectservice.api.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectsByUser();

    Project createProject(final ProjectCreateRequestDTO projectCreateRequestDTO);

    List<Project> getProjectsByName(final String name);

    boolean isProjectExists(final String name, final Long workspaceId);

    Project getProjectById(final Long id);

    void assignToProjects(final AssignToProjectRequestDTO assignToProjectRequestDTO);
}
