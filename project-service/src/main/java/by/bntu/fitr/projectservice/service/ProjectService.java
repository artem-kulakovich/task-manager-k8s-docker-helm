package by.bntu.fitr.projectservice.service;

import by.bntu.fitr.projectservice.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectsByUser();

    Project createProject(ProjectCreateRequestDTO projectCreateRequestDTO);

    List<Project> getProjectsByName(String name);

    boolean isProjectExists(String name);

    Project getProjectById(Long id);

    void assignToProjects();
}
