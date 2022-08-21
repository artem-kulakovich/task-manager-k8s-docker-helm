package by.bntu.fitr.projectservice.api.service.impl;

import by.bntu.fitr.projectservice.api.constant.CommonConstant;
import by.bntu.fitr.projectservice.api.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.api.entity.Permission;
import by.bntu.fitr.projectservice.api.entity.Project;
import by.bntu.fitr.projectservice.api.entity.ProjectInfo;
import by.bntu.fitr.projectservice.api.entity.Role;
import by.bntu.fitr.projectservice.api.exception.ProjectAlreadyExistsExceptionException;
import by.bntu.fitr.projectservice.api.jwt.JWTContext;
import by.bntu.fitr.projectservice.api.mapper.ProjectMapper;
import by.bntu.fitr.projectservice.api.service.*;
import by.bntu.fitr.projectservice.api.constant.PermissionConstant;
import by.bntu.fitr.projectservice.api.constant.RoleConstant;
import by.bntu.fitr.projectservice.api.dto.request.AssignToProjectRequestDTO;
import by.bntu.fitr.projectservice.api.exception.ProjectNotFoundException;
import by.bntu.fitr.projectservice.api.factory.ProjectFactory;
import by.bntu.fitr.projectservice.api.factory.ProjectInfoFactory;
import by.bntu.fitr.projectservice.api.factory.RoleFactory;
import by.bntu.fitr.projectservice.api.repository.ProjectRepository;
import by.bntu.fitr.projectservice.client_api.dto.response.UserClientResponseDTO;
import by.bntu.fitr.projectservice.client_api.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final JWTContext jwtContext;

    private final ProjectMapper projectMapper;

    private final ProjectFactory projectFactory;

    private final RoleFactory roleFactory;

    private final ProjectInfoFactory projectInfoFactory;

    private final ProjectInfoService projectInfoService;

    private final RoleService roleService;

    private final WorkspaceService workspaceService;

    private final PermissionService permissionService;

    private final UserClient userClient;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              JWTContext jwtContext,
                              ProjectMapper projectMapper,
                              ProjectFactory projectFactory,
                              RoleFactory roleFactory,
                              ProjectInfoFactory projectInfoFactory,
                              ProjectInfoService projectInfoService,
                              RoleService roleService,
                              WorkspaceService workspaceService,
                              PermissionService permissionService,
                              UserClient userClient) {
        this.projectRepository = projectRepository;
        this.jwtContext = jwtContext;
        this.projectMapper = projectMapper;
        this.projectFactory = projectFactory;
        this.projectInfoFactory = projectInfoFactory;
        this.roleFactory = roleFactory;
        this.projectInfoService = projectInfoService;
        this.roleService = roleService;
        this.workspaceService = workspaceService;
        this.permissionService = permissionService;
        this.userClient = userClient;
    }

    @Override
    public List<Project> getProjectsByUser() {
        return projectRepository.findProjectByUserId(jwtContext.getUserId());
    }

    @Transactional
    @Override
    public Project createProject(ProjectCreateRequestDTO projectCreateRequestDTO) {
        if (isProjectExists(projectCreateRequestDTO.getName(), projectCreateRequestDTO.getWorkspaceId())) {
            throw new ProjectAlreadyExistsExceptionException(CommonConstant.PROJECT);
        }
        Project project = projectRepository.save(projectFactory.getProject(
                projectCreateRequestDTO.getName(),
                projectCreateRequestDTO.getDescription())
        );
        Role role = roleService.createRole(RoleConstant.ROLE_PROJECT_OWNER, project);

        Permission readPermission = permissionService.getPermissionOrElseNull(PermissionConstant.PERMISSION_READ);
        Permission writePermission = permissionService.getPermissionOrElseNull(PermissionConstant.PERMISSION_WRITE);

        role.addPermission(readPermission == null
                ? permissionService.createPermission(PermissionConstant.PERMISSION_READ)
                : readPermission);
        role.addPermission(writePermission == null
                ? permissionService.createPermission(PermissionConstant.PERMISSION_WRITE)
                : writePermission);

        ProjectInfo projectInfo = projectInfoService.createProjectInfo(projectInfoFactory.getProjectInfo(
                jwtContext.getUserId(),
                role,
                project
        ));

        project.setProjectInfoList(Collections.singletonList(projectInfo));
        project.setWorkspace(workspaceService.getWorkspaceById(projectCreateRequestDTO.getWorkspaceId()));
        return project;
    }

    @Override
    public List<Project> getProjectsByName(String name) {
        return projectRepository.findProjectByNameLikeIgnoreCase(name);
    }

    @Override
    public boolean isProjectExists(String name, Long workspaceId) {
        return projectRepository.findProjectByNameAndWorkspaceId(name, workspaceId).isPresent();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(CommonConstant.PROJECT));
    }

    @Override
    public void assignToProjects(AssignToProjectRequestDTO assignToProjectRequestDTO) {
        Project project = getProjectById(assignToProjectRequestDTO.getProjectId());
        Role role = roleService.getRoleById(assignToProjectRequestDTO.getRoleId());
        UserClientResponseDTO userClientResponseDTO = userClient.getUserByEmail(assignToProjectRequestDTO.getEmail());
        projectInfoService.createProjectInfo(projectInfoFactory.getProjectInfo(userClientResponseDTO.getId(), role, project));
    }

}
