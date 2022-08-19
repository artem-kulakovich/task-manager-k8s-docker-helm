package by.bntu.fitr.projectservice.repository;


import by.bntu.fitr.projectservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p FROM Project p JOIN p.projectInfoList p_info WHERE p_info.userId = :user_id")
    List<Project> findProjectByUserId(@Param("user_id") long userId);

    Optional<Project> findProjectByName(String name);
}
