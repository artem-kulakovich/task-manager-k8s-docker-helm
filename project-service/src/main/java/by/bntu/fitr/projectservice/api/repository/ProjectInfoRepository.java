package by.bntu.fitr.projectservice.api.repository;

import by.bntu.fitr.projectservice.api.entity.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Long> {

    List<ProjectInfo> findByUserId(Long userId);
}
