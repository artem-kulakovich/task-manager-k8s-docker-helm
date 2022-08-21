package by.bntu.fitr.projectservice.api.repository;

import by.bntu.fitr.projectservice.api.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findPermissionByName(String name);
}
