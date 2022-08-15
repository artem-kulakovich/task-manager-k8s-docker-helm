package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.jwt.JWTContext;
import by.bntu.fitr.projectservice.repository.ProjectRepository;
import by.bntu.fitr.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final JWTContext jwtContext;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              JWTContext jwtContext) {
        this.projectRepository = projectRepository;
        this.jwtContext = jwtContext;
    }

    @Override
    public Project getProjectById(long id) {
        List<Project> projects = projectRepository.findByUserId(jwtContext.getUserId());
        Project result = projects.stream().filter(project -> {
            return project.getId() == id;
        }).findFirst().orElseThrow(() -> new RuntimeException(""));

        return result;
    }
}
