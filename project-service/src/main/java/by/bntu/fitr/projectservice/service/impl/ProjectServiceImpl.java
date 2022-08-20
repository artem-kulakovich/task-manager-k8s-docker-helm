package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.constant.CommonConstant;
import by.bntu.fitr.projectservice.constant.PermissionConstant;
import by.bntu.fitr.projectservice.constant.RoleConstant;
import by.bntu.fitr.projectservice.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.entity.*;
import by.bntu.fitr.projectservice.exception.ProjectInfoNofFoundException;
import by.bntu.fitr.projectservice.exception.ProjectNotFoundException;
import by.bntu.fitr.projectservice.factory.ProjectFactory;
import by.bntu.fitr.projectservice.factory.ProjectInfoFactory;
import by.bntu.fitr.projectservice.factory.RoleFactory;
import by.bntu.fitr.projectservice.jwt.JWTContext;
import by.bntu.fitr.projectservice.mapper.ProjectMapper;
import by.bntu.fitr.projectservice.repository.ProjectRepository;
import by.bntu.fitr.projectservice.service.*;
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
                              PermissionService permissionService) {
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
    }

    @Override
    public List<Project> getProjectsByUser() {
        return projectRepository.findProjectByUserId(jwtContext.getUserId());
    }

    @Transactional
    @Override
    public Project createProject(ProjectCreateRequestDTO projectCreateRequestDTO) {
        if (isProjectExists(projectCreateRequestDTO.getName())) {
            throw new ProjectInfoNofFoundException(CommonConstant.PROJECT);
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
    public boolean isProjectExists(String name) {
        return projectRepository.findProjectByName(name).isPresent();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(CommonConstant.PROJECT));
    }

    @Override
    public void assignToProjects() {

    }

}
