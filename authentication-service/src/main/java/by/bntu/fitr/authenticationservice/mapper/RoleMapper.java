package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dto.response.RoleResponseDTO;
import by.bntu.fitr.authenticationservice.entity.Permission;
import by.bntu.fitr.authenticationservice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class RoleMapper {
    private final PermissionMapper permissionMapper;

    @Autowired
    public RoleMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public RoleResponseDTO toRoleResponseDTO(final Role role) {
        return role == null
                ? null
                : new RoleResponseDTO(
                role.getId(),
                role.getName(),
                role.getCreateAt(),
                permissionMapper.toPermissionResponseDTOList(role.getRolePermissionList())
        );
    }
}
