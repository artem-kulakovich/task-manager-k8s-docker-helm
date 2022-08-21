package by.bntu.fitr.projectservice.api.repository;


import by.bntu.fitr.projectservice.api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p FROM Project p JOIN p.projectInfoList p_info WHERE p_info.userId = :user_id")
    List<Project> findProjectByUserId(@Param("user_id") Long userId);

    @Query(value = "SELECT p FROM Project p  WHERE UPPER(p.name) LIKE CONCAT('%',UPPER(:name),'%')")
    List<Project> findProjectByNameLikeIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT *FROM Project WHERE name = :name AND workspace_id = :workspaceId",
            nativeQuery = true)
    Optional<Project> findProjectByNameAndWorkspaceId(@Param("name") String name, @Param("workspaceId") Long workspaceId);
}
