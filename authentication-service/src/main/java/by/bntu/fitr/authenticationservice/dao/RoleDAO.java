package by.bntu.fitr.authenticationservice.dao;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {

    Role save(Role role);

    Optional<Role> findRoleById(final Long id);

    Optional<Role> findRoleByName(final String name);

    List<Role> findAllRoles();
}
