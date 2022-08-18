package by.bntu.fitr.projectservice.service;

import by.bntu.fitr.projectservice.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.entity.Project;

import java.util.List;

public interface ProjectService {

    Project getProjectById(long id);

    List<ProjectResponseDTO> getProjectsByUser();
    Project createProject();
}
