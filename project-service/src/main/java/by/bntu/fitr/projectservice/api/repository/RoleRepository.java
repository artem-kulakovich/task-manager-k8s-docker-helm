package by.bntu.fitr.projectservice.api.repository;

import by.bntu.fitr.projectservice.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(String name);

    List<Role> findRoleByProjectId(Long projectId);

    @Query(value = "SELECT *FROM role r JOIN project_info p_info ON p.info.role_id = r.id WHERE p_info.project_code_id = :projectId AND p_info.user_id = :userId",
            nativeQuery = true)
    Optional<Role> findRoleByUserIdAndProjectId(@Param("userId") Long userId, @Param("projectId") Long projectId);
}
