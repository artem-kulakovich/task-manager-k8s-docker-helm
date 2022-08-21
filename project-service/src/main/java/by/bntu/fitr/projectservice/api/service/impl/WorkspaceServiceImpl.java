package by.bntu.fitr.projectservice.api.service.impl;

import by.bntu.fitr.projectservice.api.entity.Workspace;
import by.bntu.fitr.projectservice.api.exception.WorkspaceAlreadyExistsException;
import by.bntu.fitr.projectservice.api.mapper.WorkspaceMapper;
import by.bntu.fitr.projectservice.api.repository.WorkspaceRepository;
import by.bntu.fitr.projectservice.api.service.WorkspaceService;
import by.bntu.fitr.projectservice.api.constant.CommonConstant;
import by.bntu.fitr.projectservice.api.dto.request.WorkspaceCreateRequestDTO;
import by.bntu.fitr.projectservice.api.exception.WorkspaceNotFoundException;
import by.bntu.fitr.projectservice.api.jwt.JWTContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final JWTContext jwtContext;

    @Autowired
    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository,
                                WorkspaceMapper workspaceMapper,
                                JWTContext jwtContext) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceMapper = workspaceMapper;
        this.jwtContext = jwtContext;
    }

    @Override
    public Workspace createWorkspace(final WorkspaceCreateRequestDTO workspaceCreateRequestDTO) {
        if (isWorkspaceExists(workspaceCreateRequestDTO.getName())) {
            throw new WorkspaceAlreadyExistsException(CommonConstant.WORKSPACE);
        }
        Workspace workspace = workspaceMapper.toWorkSpace(workspaceCreateRequestDTO);
        workspace.setUserId(jwtContext.getUserId());
        return workspaceRepository.save(workspace);
    }

    @Override
    public Workspace getWorkspaceById(Long id) {
        return workspaceRepository.findById(id).orElseThrow(() -> new WorkspaceNotFoundException(CommonConstant.WORKSPACE));
    }

    @Override
    public List<Workspace> getAllWorkspacesBelongedToCurrentUser() {
        return workspaceRepository.findWorkspaceByUserId(jwtContext.getUserId());
    }

    @Override
    public boolean isWorkspaceExists(String name) {
        return workspaceRepository.findWorkspaceByName(name).isPresent();
    }
}
