package by.bntu.fitr.projectservice.api.service;

import by.bntu.fitr.projectservice.api.entity.Project;
import by.bntu.fitr.projectservice.api.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(final String name);

    List<Role> getRoleBelongedToCurrentProject(final Long projectId);

    Role createRole(final String name, final Project project);

    Role getRoleById(final Long id);
}
