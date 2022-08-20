package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.entity.Role;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    List<String> getRolePermissionsName(final Role role);

}
