package by.bntu.fitr.projectservice.repository;

import by.bntu.fitr.projectservice.entity.Project;
import by.bntu.fitr.projectservice.entity.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Long> {

    List<ProjectInfo> findByUserId(Long userId);
}
