package by.bntu.fitr.authenticationservice.handler;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.response.PermissionResponseDTO;
import by.bntu.fitr.authenticationservice.dto.response.RoleResponseDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.mapper.PermissionMapper;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperHandler {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    public MapperHandler(final UserMapper userMapper,
                         final RoleMapper roleMapper,
                         final PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    public UserResponseDTO executeUserResponseDTOWithInherit(User user, int inheritLvl) {
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        if (inheritLvl >= 1 && user.getRole() != null) {
            userResponseDTO.setRoleResponseDTO(executeRoleResponseDTOWithInherit(user.getRole(), --inheritLvl));
        }
        return userResponseDTO;
    }

    public RoleResponseDTO executeRoleResponseDTOWithInherit(Role role, int inherit) {
        RoleResponseDTO roleResponseDTO = roleMapper.toRoleResponseDTO(role);
        if (inherit >= 1 && role.getPermissionList() != null) {
            roleResponseDTO.setPermissionResponseDTOList(executePermissionResponseDTOWithInherit(
                    role.getPermissionList()));
        }
        return roleResponseDTO;
    }

    public List<PermissionResponseDTO> executePermissionResponseDTOWithInherit(List<Permission> permissionList) {
        List<PermissionResponseDTO> permissionResponseDTOList = permissionMapper
                .toPermissionResponseDTOList(permissionList);
        return permissionResponseDTOList;
    }
}
