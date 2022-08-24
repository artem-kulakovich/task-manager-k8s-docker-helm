package by.bntu.fitr.projectservice.api.mapper;

import by.bntu.fitr.projectservice.api.dto.response.RoleResponseDTO;
import by.bntu.fitr.projectservice.api.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
                permissionMapper.toPermissionResponseDTOList(role.getPermissionList())
        );
    }

    public List<RoleResponseDTO> toRoleResponseDTOList(final List<Role> roleList) {
        return roleList == null
                ? null
                : roleList.stream().map(this::toRoleResponseDTO)
                .collect(Collectors.toList());

    }
}
