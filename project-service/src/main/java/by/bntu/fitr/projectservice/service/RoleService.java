package by.bntu.fitr.projectservice.service;

import by.bntu.fitr.projectservice.dto.request.CreateRoleRequestDTO;
import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);

    List<Role> getRoleBelongedToCurrentProject(Long projectId);

    Role createRole(String name, Project project);
}
