package by.bntu.fitr.authenticationservice.repository;

import by.bntu.fitr.authenticationservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Deprecated
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findPermissionByName(String name);
}
