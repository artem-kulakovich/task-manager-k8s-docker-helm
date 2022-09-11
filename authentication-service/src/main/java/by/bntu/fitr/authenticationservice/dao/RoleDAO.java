package by.bntu.fitr.authenticationservice.dao;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {

    Role save(final Role role);

    Optional<Role> findRoleById(final Long id, final String fetchType);

    Optional<Role> findRoleByName(final String name, final String fetchType);

    List<Role> findAllRoles(final String fetchType);
}
