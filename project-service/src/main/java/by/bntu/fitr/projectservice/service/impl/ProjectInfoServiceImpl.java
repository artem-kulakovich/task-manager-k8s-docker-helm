package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import by.bntu.fitr.projectservice.repository.ProjectInfoRepository;
import by.bntu.fitr.projectservice.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectInfoServiceImpl implements ProjectInfoService {
    private ProjectInfoRepository projectInfoRepository;

    @Autowired
    public ProjectInfoServiceImpl(ProjectInfoRepository projectInfoRepository) {
        this.projectInfoRepository = projectInfoRepository;
    }

    @Override
    public List<ProjectInfo> getProjectById(long id) {
        return projectInfoRepository.findByUserId(id);
    }
}
