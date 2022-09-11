package by.bntu.fitr.authenticationservice.service;


import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;

public interface RoleService {

    Role getRoleByName(final String name, final String fetchType);

    String getRoleName(final Role role);

    @Deprecated
    Role createRole(final String name);

    Role getRoleByNameOrElseNull(final String name, final String fetchType);

    Role createIfNotExists(final String name);
}

