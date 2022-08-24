package by.bntu.fitr.projectservice.api.rest;

import by.bntu.fitr.projectservice.api.dto.response.WorkspaceResponseDTO;
import by.bntu.fitr.projectservice.api.mapper.WorkspaceMapper;
import by.bntu.fitr.projectservice.api.service.WorkspaceService;
import by.bntu.fitr.projectservice.api.dto.request.WorkspaceCreateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/project-service/workspaces")
public class WorkspaceRestController {
    private final WorkspaceService workspaceService;
    private final WorkspaceMapper workspaceMapper;

    @Autowired
    public WorkspaceRestController(WorkspaceService workspaceService,
                                   WorkspaceMapper workspaceMapper) {
        this.workspaceService = workspaceService;
        this.workspaceMapper = workspaceMapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<WorkspaceResponseDTO> createWorkspace(@RequestBody final WorkspaceCreateRequestDTO workspaceCreateRequestDTO) {
        return new ResponseEntity<>(workspaceMapper
                .toWorkspaceResponseDTO(workspaceService.createWorkspace(workspaceCreateRequestDTO)), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<WorkspaceResponseDTO>> getAllWorkspaces() {
        return new ResponseEntity<>(workspaceMapper
                .toWorkspaceResponseDTOList(workspaceService.getAllWorkspacesBelongedToCurrentUser()), HttpStatus.OK);
    }
}
