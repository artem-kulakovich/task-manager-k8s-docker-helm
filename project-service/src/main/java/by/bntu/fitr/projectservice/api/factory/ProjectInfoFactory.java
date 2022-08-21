package by.bntu.fitr.projectservice.api.factory;

import by.bntu.fitr.projectservice.api.entity.Project;
import by.bntu.fitr.projectservice.api.entity.ProjectInfo;
import by.bntu.fitr.projectservice.api.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfoFactory {

    public ProjectInfo getProjectInfo(Long userId, Role role, Project project) {
        return new ProjectInfo(
                userId,
                role,
                project
        );
    }
}
