package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.entity.ProjectRole;
import by.bntu.fitr.authenticationservice.service.ProjectRoleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectRoleServiceImpl implements ProjectRoleService {
    @Override
    public List<String> getProjectRoleNames(List<ProjectRole> roleList) {
        return roleList == null ? Collections.emptyList()
                : roleList.stream().map(ProjectRole::getName)
                .collect(Collectors.toList());
    }
}
