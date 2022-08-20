package by.bntu.fitr.projectservice.repository;

import by.bntu.fitr.projectservice.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    @Query(value = "SELECT  *FROM workspace w JOIN project p ON p.workspace_id = w.id JOIN project_info p_info ON p_info.project_code_id = p.id WHERE p_info.user_id = :userId",
            nativeQuery = true)
    List<Workspace> findWorkspaceByUserId(@Param("userId") Long userId);
}
