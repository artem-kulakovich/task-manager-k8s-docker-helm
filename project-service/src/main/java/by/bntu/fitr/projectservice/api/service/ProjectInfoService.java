package by.bntu.fitr.projectservice.api.service;

import by.bntu.fitr.projectservice.api.entity.ProjectInfo;
import org.springframework.stereotype.Service;

@Service
public interface ProjectInfoService {

    ProjectInfo createProjectInfo(final ProjectInfo projectInfo);
}
