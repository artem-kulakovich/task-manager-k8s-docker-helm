package by.bntu.fitr.projectservice.api.factory;

import by.bntu.fitr.projectservice.api.entity.Project;
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
