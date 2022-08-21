package by.bntu.fitr.projectservice.api.factory;

import by.bntu.fitr.projectservice.api.entity.Role;
import by.bntu.fitr.projectservice.api.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {

    public Role getRole(String name, Project project) {
        return new Role(
                name,
                project
        );
    }
}
