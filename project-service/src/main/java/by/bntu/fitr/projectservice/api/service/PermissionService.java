package by.bntu.fitr.projectservice.api.service;


import by.bntu.fitr.projectservice.api.entity.Permission;

public interface PermissionService {

    Permission createPermission(final String name);

    boolean isPermissionExists(final String name);

    Permission getPermissionOrElseNull(final String name);
}
