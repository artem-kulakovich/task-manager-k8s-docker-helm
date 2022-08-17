package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.entity.Permission;
import by.bntu.fitr.authenticationservice.entity.ProjectRole;
import by.bntu.fitr.authenticationservice.entity.Role;
import by.bntu.fitr.authenticationservice.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<String> getRolePermissionsName(final Role role) {
        if (role == null) {
            return Collections.emptyList();
        }

        List<Permission> permissionList = role.getRolePermissionList();
        return permissionList == null ? Collections.emptyList()
                : permissionList.stream().map(Permission::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<String>> getProjectRolePermissionsName(List<ProjectRole> projectRoleList) {
        if (projectRoleList == null) {
            return Collections.emptyMap();
        }

        Map<String, List<String>> projectRolePermissions = new HashMap<>();

        for (ProjectRole projectRole : projectRoleList) {
            projectRolePermissions.put(projectRole.getName(),
                    projectRole.getProjectRolePermissionList().stream().map(Permission::getName).collect(Collectors.toList()));
        }
        return projectRolePermissions;
    }
}
