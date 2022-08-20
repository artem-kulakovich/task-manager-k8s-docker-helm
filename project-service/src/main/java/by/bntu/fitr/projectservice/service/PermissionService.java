package by.bntu.fitr.projectservice.service;


import by.bntu.fitr.projectservice.entity.Permission;

public interface PermissionService {

    Permission createPermission(String name);

    boolean isPermissionExists(String name);

    Permission getPermissionOrElseNull(String name);
}
