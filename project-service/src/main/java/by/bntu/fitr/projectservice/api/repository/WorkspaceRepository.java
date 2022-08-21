package by.bntu.fitr.projectservice.api.repository;

import by.bntu.fitr.projectservice.api.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    List<Workspace> findWorkspaceByUserId(Long userId);

    Optional<Workspace> findWorkspaceByName(String name);
}
