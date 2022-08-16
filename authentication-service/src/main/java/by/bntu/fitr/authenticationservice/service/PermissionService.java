package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.entity.ProjectRole;
import by.bntu.fitr.authenticationservice.entity.Role;

import java.util.List;

public interface PermissionService {

    List<String> getRolePermissionsName(Role role);

    List<String> getProjectRolePermissionsName(ProjectRole projectRole);
}
