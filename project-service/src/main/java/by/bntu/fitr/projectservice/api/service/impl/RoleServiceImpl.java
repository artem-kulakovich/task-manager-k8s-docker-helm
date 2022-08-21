package by.bntu.fitr.projectservice.api.service.impl;

import by.bntu.fitr.projectservice.api.constant.CommonConstant;
import by.bntu.fitr.projectservice.api.entity.Project;
import by.bntu.fitr.projectservice.api.entity.Role;
import by.bntu.fitr.projectservice.api.exception.RoleNotFoundException;
import by.bntu.fitr.projectservice.api.factory.RoleFactory;
import by.bntu.fitr.projectservice.api.repository.RoleRepository;
import by.bntu.fitr.projectservice.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleFactory roleFactory;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           RoleFactory roleFactory) {
        this.roleFactory = roleFactory;
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() -> new RoleNotFoundException(CommonConstant.ROLE));
    }

    @Override
    public List<Role> getRoleBelongedToCurrentProject(Long projectId) {
        return roleRepository.findRoleByProjectId(projectId);
    }

    @Override
    public Role createRole(String name, Project project) {
        return roleRepository.save(roleFactory.getRole(name, project));
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(CommonConstant.ROLE));
    }
}
