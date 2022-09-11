package by.bntu.fitr.authenticationservice.service;




import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;

import java.util.List;

public interface PermissionService {

    List<String> getRolePermissionsName(final Role role);

    Permission createIfNotExists(final String name);

    Permission getPermissionByNameOrElseNull(final String name, final String fetchType);

}
