package by.bntu.fitr.projectservice.mapper;

import by.bntu.fitr.projectservice.dto.response.RoleResponseDTO;
import by.bntu.fitr.projectservice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    private final PermissionMapper permissionMapper;

    @Autowired
    public RoleMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public RoleResponseDTO toRoleResponseDTO(Role role) {
        return role == null
                ? null
                : new RoleResponseDTO(
                role.getId(),
                role.getName(),
                permissionMapper.toPermissionResponseDTO(role.getPermissionList())
        );
    }
}
