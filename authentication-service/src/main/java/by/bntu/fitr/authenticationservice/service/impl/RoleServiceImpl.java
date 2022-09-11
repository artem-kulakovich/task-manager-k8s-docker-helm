package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.RoleDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.exception.RoleNotFoundException;
import by.bntu.fitr.authenticationservice.factory.RoleFactory;
import by.bntu.fitr.authenticationservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;
    private final RoleFactory roleFactory;

    @Autowired
    public RoleServiceImpl(final RoleDAO roleDAO,
                           final RoleFactory roleFactory) {
        this.roleDAO = roleDAO;
        this.roleFactory = roleFactory;
    }

    @Override
    public Role getRoleByName(final String name, final String fetchType) {
        return roleDAO.findRoleByName(name, fetchType).orElseThrow(
                () -> new RoleNotFoundException(CommonConstant.ROLE)
        );
    }

    @Override
    public String getRoleName(final Role role) {
        return role == null ? CommonConstant.EMPTY_STRING : role.getName();
    }

    @Override
    public Role createRole(final String name) {
        return roleDAO.save(roleFactory.getRole(name));
    }

    @Override
    public Role getRoleByNameOrElseNull(final String name, final String fetchType) {
        return roleDAO.findRoleByName(name, fetchType).orElse(null);
    }

    @Override
    public Role createIfNotExists(final String name) {
        Role role = getRoleByNameOrElseNull(name, CommonConstant.FetchType.LAZY);
        if (role == null) {
            role = roleDAO.save(roleFactory.getRole(name));
        }
        return role;
    }
}
