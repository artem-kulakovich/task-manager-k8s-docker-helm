package by.bntu.fitr.authenticationservice.repository;

import by.bntu.fitr.authenticationservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
