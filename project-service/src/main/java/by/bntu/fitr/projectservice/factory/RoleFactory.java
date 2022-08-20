package by.bntu.fitr.projectservice.factory;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.Role;
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
