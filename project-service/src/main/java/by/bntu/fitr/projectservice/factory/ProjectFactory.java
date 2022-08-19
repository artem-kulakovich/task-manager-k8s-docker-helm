package by.bntu.fitr.projectservice.factory;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import org.springframework.stereotype.Component;

@Component
public class ProjectFactory {

    public Project getProject(String name, String description) {
        return new Project(
                name,
                description
        );
    }
}
