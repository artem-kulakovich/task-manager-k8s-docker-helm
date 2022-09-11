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

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class MapperHandler {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    private final OffsetDateTime offsetDateTime = OffsetDateTime.now();

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

    /*
    public List<UserResponseDTO> userResponseDTOList(List<by.bntu.fitr.authenticationservice.entity.User> userList) {
        return userList.stream().map((user) ->
                new UserResponseDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUserName(),
                        user.getEmail(),
                        offsetDateTime,
                        1,
                        roleResponseDTO(user.getRole())
                )).collect(Collectors.toList());
    }

    public RoleResponseDTO roleResponseDTO(by.bntu.fitr.authenticationservice.entity.Role role) {
        return new RoleResponseDTO(
                role.getId(),
                role.getName(),
                offsetDateTime,
                permissionResponseDTOList(role.getRolePermissionList())
        );
    }

    public List<PermissionResponseDTO> permissionResponseDTOList(List<by.bntu.fitr.authenticationservice.entity.Permission> permissionList) {
        return permissionList.stream().map((permission -> {
            return new PermissionResponseDTO(
                    permission.getId(),
                    permission.getName(),
                    offsetDateTime);
        })).collect(Collectors.toList());
    }

     */
}
