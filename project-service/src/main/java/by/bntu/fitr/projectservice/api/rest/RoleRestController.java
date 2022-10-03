package by.bntu.fitr.projectservice.api.rest;

import by.bntu.fitr.projectservice.api.dto.response.RoleResponseDTO;
import by.bntu.fitr.projectservice.api.dto.request.CreateRoleRequestDTO;
import by.bntu.fitr.projectservice.api.entity.Project;
import by.bntu.fitr.projectservice.api.mapper.RoleMapper;
import by.bntu.fitr.projectservice.api.service.ProjectService;
import by.bntu.fitr.projectservice.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/project-service/roles")
public class RoleRestController {
    private final RoleService roleService;
    private final ProjectService projectService;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleRestController(RoleService roleService,
                              RoleMapper roleMapper,
                              ProjectService projectService) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.projectService = projectService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<RoleResponseDTO>> getAllRolesBelongedToCurrentProject(@RequestParam("projectId") final Long projectId) {
        return new ResponseEntity<>(roleMapper.toRoleResponseDTOList(roleService.getRoleBelongedToCurrentProject(projectId)),
                HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody final CreateRoleRequestDTO createRoleRequestDTO) {
        Project project = projectService.getProjectById(createRoleRequestDTO.getProjectId());
        return new ResponseEntity<>(roleMapper.toRoleResponseDTO(roleService.createRole(createRoleRequestDTO.getName(), project)),
                HttpStatus.OK);
    }
}
