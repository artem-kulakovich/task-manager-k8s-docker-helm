package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.entity.ProjectRole;


import java.util.List;

public interface ProjectRoleService {

    List<String> getProjectRoleNames(List<ProjectRole> roleList);
}
