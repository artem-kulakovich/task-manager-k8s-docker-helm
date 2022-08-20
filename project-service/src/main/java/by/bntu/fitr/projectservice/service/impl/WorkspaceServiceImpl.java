package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.constant.CommonConstant;
import by.bntu.fitr.projectservice.dto.request.WorkspaceCreateRequestDTO;
import by.bntu.fitr.projectservice.entity.Workspace;
import by.bntu.fitr.projectservice.exception.WorkspaceNotFoundException;
import by.bntu.fitr.projectservice.jwt.JWTContext;
import by.bntu.fitr.projectservice.mapper.WorkspaceMapper;
import by.bntu.fitr.projectservice.repository.WorkspaceRepository;
import by.bntu.fitr.projectservice.service.WorkspaceService;
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
}
