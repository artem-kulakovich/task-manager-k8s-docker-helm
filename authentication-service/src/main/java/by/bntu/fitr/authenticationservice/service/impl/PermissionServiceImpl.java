package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.PermissionDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.factory.PermissionFactory;
import by.bntu.fitr.authenticationservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDAO permissionDAO;
    private final PermissionFactory permissionFactory;

    @Autowired
    public PermissionServiceImpl(final PermissionDAO permissionDAO,
                                 final PermissionFactory permissionFactory) {
        this.permissionDAO = permissionDAO;
        this.permissionFactory = permissionFactory;
    }

    @Override
    public List<String> getRolePermissionsName(final Role role) {
        if (role == null) {
            return Collections.emptyList();
        }

        List<Permission> permissionList = role.getPermissionList();
        return permissionList == null ? Collections.emptyList()
                : permissionList.stream().map(Permission::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Permission createIfNotExists(final String name) {
        Permission permission = getPermissionByNameOrElseNull(name, CommonConstant.FetchType.LAZY);
        if (permission == null) {
            permission = permissionDAO.save(permissionFactory.getPermission(name));
        }
        return permission;
    }

    @Override
    public Permission getPermissionByNameOrElseNull(final String name, final String fetchType) {
        return permissionDAO.findPermissionByName(name, fetchType).orElse(null);
    }

}
