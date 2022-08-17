package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.entity.Role;
import by.bntu.fitr.authenticationservice.exception.RoleNotFoundException;
import by.bntu.fitr.authenticationservice.repository.RoleRepository;
import by.bntu.fitr.authenticationservice.service.RoleService;
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
        return roleRepository.findByName(name).orElseThrow(
                () -> new RoleNotFoundException("Role")
        );
    }

    @Override
    public String getRoleName(final Role role) {
        return role == null ? CommonConstant.EMPTY_STRING : role.getName();
    }
}
