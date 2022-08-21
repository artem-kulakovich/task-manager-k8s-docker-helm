package by.bntu.fitr.projectservice.api.service.impl;

import by.bntu.fitr.projectservice.api.entity.ProjectInfo;
import by.bntu.fitr.projectservice.api.repository.ProjectInfoRepository;
import by.bntu.fitr.projectservice.api.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {
    private ProjectInfoRepository projectInfoRepository;

    @Autowired
    public ProjectInfoServiceImpl(ProjectInfoRepository projectInfoRepository) {
        this.projectInfoRepository = projectInfoRepository;
    }


    @Override
    public ProjectInfo createProjectInfo(ProjectInfo projectInfo) {
        return projectInfoRepository.save(projectInfo);
    }
}
