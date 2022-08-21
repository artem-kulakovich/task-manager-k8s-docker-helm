package by.bntu.fitr.projectservice.api.service;

import by.bntu.fitr.projectservice.api.entity.Project;
import by.bntu.fitr.projectservice.api.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);

    List<Role> getRoleBelongedToCurrentProject(Long projectId);

    Role createRole(String name, Project project);

    Role getRoleById(Long id);
}
