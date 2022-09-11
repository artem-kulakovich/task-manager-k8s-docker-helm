package by.bntu.fitr.authenticationservice.dao;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionDAO {

    Permission save(final Permission permission);

    Optional<Permission> findPermissionById(final Long id, final String fetchType);

    Optional<Permission> findPermissionByName(final String name, final String fetchType);

    List<Permission> findAllPermissions(final String fetchType);


}
