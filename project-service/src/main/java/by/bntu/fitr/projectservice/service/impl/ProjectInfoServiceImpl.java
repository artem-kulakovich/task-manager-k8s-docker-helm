package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import by.bntu.fitr.projectservice.repository.ProjectInfoRepository;
import by.bntu.fitr.projectservice.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
