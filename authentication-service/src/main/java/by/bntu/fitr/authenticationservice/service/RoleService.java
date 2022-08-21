package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.entity.Role;

public interface RoleService {

    Role getRoleByName(final String name);

    String getRoleName(final Role role);

    @Deprecated
    Role createRole(final String name);

    Role getRoleByNameOrElseNull(final String name);

    Role createIfNotExists(final String name);
}

