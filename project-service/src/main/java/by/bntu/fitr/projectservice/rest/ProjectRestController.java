package by.bntu.fitr.projectservice.rest;

import by.bntu.fitr.projectservice.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.jwt.JWTContext;
import by.bntu.fitr.projectservice.jwt.JWTTokenProvider;
import by.bntu.fitr.projectservice.mapper.ProjectMapper;
import by.bntu.fitr.projectservice.service.ProjectService;
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
    public ResponseEntity<ProjectResponseDTO> creatProject(@RequestBody ProjectCreateRequestDTO projectCreateRequestDTO) {
        return new ResponseEntity<>(projectMapper
                .toProjectResponseDTO(projectService.createProject(projectCreateRequestDTO)), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-name")
    public ResponseEntity<List<ProjectResponseDTO>> findProjectsByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(projectMapper.toProjectResponseDTOList(projectService.getProjectsByName(name)),
                HttpStatus.OK);
    }
}
