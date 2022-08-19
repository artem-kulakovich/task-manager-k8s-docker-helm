package by.bntu.fitr.projectservice.factory;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import by.bntu.fitr.projectservice.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfoFactory {

    public ProjectInfo getProjectInfo(long userId, Role role, Project project) {
        return new ProjectInfo(
                userId,
                role,
                project
        );
    }
}
