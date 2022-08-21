package by.bntu.fitr.projectservice.api.repository;

import by.bntu.fitr.projectservice.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(String name);

    List<Role> findRoleByProjectId(Long projectId);
}
