package by.bntu.fitr.projectservice.rest;

import by.bntu.fitr.projectservice.dto.request.CreateRoleRequestDTO;
import by.bntu.fitr.projectservice.dto.response.RoleResponseDTO;
import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.Role;
import by.bntu.fitr.projectservice.mapper.RoleMapper;
import by.bntu.fitr.projectservice.service.ProjectService;
import by.bntu.fitr.projectservice.service.RoleService;
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

    @GetMapping(value = "/")
    public ResponseEntity<List<RoleResponseDTO>> getAllRolesBelongedToCurrentProject(@RequestParam("projectId") Long projectId) {
        return new ResponseEntity<>(roleMapper.toRoleResponseDTOList(roleService.getRoleBelongedToCurrentProject(projectId)),
                HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody CreateRoleRequestDTO createRoleRequestDTO) {
        Project project = projectService.getProjectById(createRoleRequestDTO.getProjectId());
        return new ResponseEntity<>(roleMapper.toRoleResponseDTO(roleService.createRole(createRoleRequestDTO.getName(), project)),
                HttpStatus.OK);
    }
}
