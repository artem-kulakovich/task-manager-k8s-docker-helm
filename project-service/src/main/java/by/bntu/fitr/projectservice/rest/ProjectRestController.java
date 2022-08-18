package by.bntu.fitr.projectservice.rest;

import by.bntu.fitr.projectservice.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.jwt.JWTContext;
import by.bntu.fitr.projectservice.jwt.JWTTokenProvider;
import by.bntu.fitr.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectRestController {
    private final ProjectService projectService;
    private final JWTContext jwtContext;

    @Autowired
    public ProjectRestController(ProjectService projectService,
                                 JWTContext jwtContext) {
        this.projectService = projectService;
        this.jwtContext = jwtContext;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/by-user")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsByUser() {
        return new ResponseEntity<>(projectService.getProjectsByUser(), HttpStatus.OK);
    }

}
