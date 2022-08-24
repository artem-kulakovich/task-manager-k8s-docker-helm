package by.bntu.fitr.projectservice.api.rest;

import by.bntu.fitr.projectservice.api.dto.request.AssignToProjectRequestDTO;
import by.bntu.fitr.projectservice.api.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.api.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.api.mapper.ProjectMapper;
import by.bntu.fitr.projectservice.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/project-service/projects")
public class ProjectRestController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectRestController(ProjectService projectService,
                                 ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping(value = "/by-user")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsByUser() {
        return new ResponseEntity<>(projectMapper.toProjectResponseDTOList(projectService.getProjectsByUser()),
                HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody final ProjectCreateRequestDTO projectCreateRequestDTO) {
        return new ResponseEntity<>(projectMapper
                .toProjectResponseDTO(projectService.createProject(projectCreateRequestDTO)), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-name")
    public ResponseEntity<List<ProjectResponseDTO>> findProjectsByName(@RequestParam("name") final String name) {
        return new ResponseEntity<>(projectMapper.toProjectResponseDTOList(projectService.getProjectsByName(name)),
                HttpStatus.OK);
    }

    @PostMapping(value = "/assign-to-project")
    public ResponseEntity<?> assignToProject(@RequestBody final AssignToProjectRequestDTO assignToProjectRequestDTO) {
        projectService.assignToProjects(assignToProjectRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
