package by.bntu.fitr.projectservice.repository;


import by.bntu.fitr.projectservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUserId(long userId);
}
