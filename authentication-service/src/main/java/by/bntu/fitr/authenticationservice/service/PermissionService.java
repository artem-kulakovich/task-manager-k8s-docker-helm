package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.entity.Permission;
import by.bntu.fitr.authenticationservice.entity.Role;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    List<String> getRolePermissionsName(final Role role);

    Permission createIfNotExists(final String name);

    Permission getPermissionByNameOrElseNull(final String name);

}
