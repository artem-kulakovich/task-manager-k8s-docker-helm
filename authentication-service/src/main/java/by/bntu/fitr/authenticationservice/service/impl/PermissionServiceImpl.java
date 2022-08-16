package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.entity.Permission;
import by.bntu.fitr.authenticationservice.entity.ProjectRole;
import by.bntu.fitr.authenticationservice.entity.Role;
import by.bntu.fitr.authenticationservice.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<String> getRolePermissionsName(Role role) {
        if (role == null) {
            return Collections.emptyList();
        }

        List<Permission> permissionList = role.getRolePermissionList();
        return permissionList == null ? Collections.emptyList()
                : permissionList.stream().map(Permission::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getProjectRolePermissionsName(ProjectRole projectRole) {
        if (projectRole == null) {
            return Collections.emptyList();
        }

        List<Permission> permissionList = projectRole.getProjectRolePermissionList();
        return permissionList == null ? Collections.emptyList()
                : projectRole.getProjectRolePermissionList().stream().map(Permission::getName)
                .collect(Collectors.toList());
    }
}
