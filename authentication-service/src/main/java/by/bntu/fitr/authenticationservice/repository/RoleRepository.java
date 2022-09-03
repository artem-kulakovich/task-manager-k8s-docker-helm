package by.bntu.fitr.authenticationservice.repository;

import by.bntu.fitr.authenticationservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Deprecated
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
