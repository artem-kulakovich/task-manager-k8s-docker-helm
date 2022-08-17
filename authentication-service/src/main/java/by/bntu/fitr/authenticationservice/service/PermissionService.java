package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.entity.ProjectRole;
import by.bntu.fitr.authenticationservice.entity.Role;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    List<String> getRolePermissionsName(Role role);

    Map<String, List<String>> getProjectRolePermissionsName(List<ProjectRole> projectRoleList);
}
