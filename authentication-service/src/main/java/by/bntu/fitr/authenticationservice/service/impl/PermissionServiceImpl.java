package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.entity.Permission;
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

}
