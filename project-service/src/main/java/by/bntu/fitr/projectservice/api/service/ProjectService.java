package by.bntu.fitr.projectservice.api.service;

import by.bntu.fitr.projectservice.api.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.api.dto.request.AssignToProjectRequestDTO;
import by.bntu.fitr.projectservice.api.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectsByUser();

    Project createProject(ProjectCreateRequestDTO projectCreateRequestDTO);

    List<Project> getProjectsByName(String name);

    boolean isProjectExists(String name, Long workspaceId);

    Project getProjectById(Long id);

    void assignToProjects(AssignToProjectRequestDTO assignToProjectRequestDTO);
}
