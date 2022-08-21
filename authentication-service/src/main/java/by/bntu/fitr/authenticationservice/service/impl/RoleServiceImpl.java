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
    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(final String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new RoleNotFoundException(CommonConstant.ROLE)
        );
    }

    @Override
    public String getRoleName(final Role role) {
        return role == null ? CommonConstant.EMPTY_STRING : role.getName();
    }

    @Override
    public Role createRole(final String name) {
        return roleRepository.save(new Role(name));
    }

    @Override
    public Role getRoleByNameOrElseNull(final String name) {
        return roleRepository.findByName(name).orElse(null);
    }

    @Override
    public Role createIfNotExists(final String name) {
        Role role = getRoleByNameOrElseNull(name);
        if (role == null) {
            role = roleRepository.save(new Role(name));
        }
        return role;
    }
}
