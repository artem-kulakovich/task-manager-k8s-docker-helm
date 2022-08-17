package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.jwt.JWTContext;
import by.bntu.fitr.projectservice.repository.ProjectRepository;
import by.bntu.fitr.projectservice.service.ProjectInfoService;
import by.bntu.fitr.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final JWTContext jwtContext;

    private final ProjectInfoService projectInfoService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              JWTContext jwtContext,
                              ProjectInfoService projectInfoService) {
        this.projectRepository = projectRepository;
        this.jwtContext = jwtContext;
        this.projectInfoService = projectInfoService;
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
}
