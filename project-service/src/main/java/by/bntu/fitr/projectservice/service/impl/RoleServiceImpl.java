package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.entity.Role;
import by.bntu.fitr.projectservice.repository.RoleRepository;
import by.bntu.fitr.projectservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() -> new RuntimeException());
    }
}
