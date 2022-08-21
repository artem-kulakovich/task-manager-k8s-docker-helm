package by.bntu.fitr.projectservice.api.service;


import by.bntu.fitr.projectservice.api.entity.Permission;

public interface PermissionService {

    Permission createPermission(String name);

    boolean isPermissionExists(String name);

    Permission getPermissionOrElseNull(String name);
}
