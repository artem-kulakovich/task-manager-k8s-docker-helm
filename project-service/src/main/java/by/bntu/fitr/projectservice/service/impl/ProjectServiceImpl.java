package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.constant.RoleConstant;
import by.bntu.fitr.projectservice.dto.request.ProjectCreateRequestDTO;
import by.bntu.fitr.projectservice.dto.response.ProjectResponseDTO;
import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import by.bntu.fitr.projectservice.entity.Role;
import by.bntu.fitr.projectservice.exception.ProjectInfoNofFoundException;
import by.bntu.fitr.projectservice.factory.ProjectFactory;
import by.bntu.fitr.projectservice.factory.ProjectInfoFactory;
import by.bntu.fitr.projectservice.jwt.JWTContext;
import by.bntu.fitr.projectservice.mapper.ProjectMapper;
import by.bntu.fitr.projectservice.repository.ProjectRepository;
import by.bntu.fitr.projectservice.service.ProjectInfoService;
import by.bntu.fitr.projectservice.service.ProjectService;
import by.bntu.fitr.projectservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final JWTContext jwtContext;

    private final ProjectMapper projectMapper;

    private final ProjectFactory projectFactory;

    private final ProjectInfoFactory projectInfoFactory;

    private final ProjectInfoService projectInfoService;

    private final RoleService roleService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              JWTContext jwtContext,
                              ProjectMapper projectMapper,
                              ProjectFactory projectFactory,
                              ProjectInfoFactory projectInfoFactory,
                              ProjectInfoService projectInfoService,
                              RoleService roleService) {
        this.projectRepository = projectRepository;
        this.jwtContext = jwtContext;
        this.projectMapper = projectMapper;
        this.projectFactory = projectFactory;
        this.projectInfoFactory = projectInfoFactory;
        this.projectInfoService = projectInfoService;
        this.roleService = roleService;
    }

    @Override
    public Project getProjectById(long id) {
        /*
        Project result = projects.stream().filter(project -> {
            return project.getId() == id;
        }).findFirst().orElseThrow(() -> new RuntimeException(""));


         */
        return null;
    }

    @Override
    public List<ProjectResponseDTO> getProjectsByUser() {
        return projectMapper.toProjectResponseDTOList(projectRepository.findProjectByUserId(jwtContext.getUserId()));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Project createProject(ProjectCreateRequestDTO projectCreateRequestDTO) {
        if (isProjectExists(projectCreateRequestDTO.getName())) {
            throw new RuntimeException("");
        }
        Project project = projectRepository.save(projectFactory.getProject(
                projectCreateRequestDTO.getName(),
                projectCreateRequestDTO.getDescription())
        );

        Role role = roleService.getRoleByName(RoleConstant.ROLE_PROJECT_OWNER);

        projectInfoService.createProjectInfo(projectInfoFactory.getProjectInfo(
                jwtContext.getUserId(),
                role,
                project
        ));

        return project;
    }

    @Override
    public boolean isProjectExists(String name) {
        return projectRepository.findProjectByName(name).isPresent();
    }


}
