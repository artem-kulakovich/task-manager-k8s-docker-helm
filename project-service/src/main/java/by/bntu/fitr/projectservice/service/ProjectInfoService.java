package by.bntu.fitr.projectservice.service;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectInfoService {

    List<ProjectInfo> getProjectByUserId(long id);
}
