package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.entity.Role;
import by.bntu.fitr.authenticationservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserMapper {
    private final RoleMapper roleMapper;

    @Autowired
    public UserMapper(final RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User toUser(final UserCreateRequestDTO userCreateRequestDTO) {
        return new User(
                userCreateRequestDTO.getFirstName(),
                userCreateRequestDTO.getLastName(),
                userCreateRequestDTO.getUserName(),
                userCreateRequestDTO.getPassword(),
                userCreateRequestDTO.getEmail()
        );
    }

    public UserResponseDTO toUserResponseDTO(final User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateAt(),
                roleMapper.toRoleResponseDTO(user.getRole())
        );
    }

    public List<UserResponseDTO> toUserResponseDTOList(final List<User> userList) {
        return userList == null
                ? Collections.emptyList()
                : userList.stream().map((this::toUserResponseDTO))
                .collect(Collectors.toList());
    }
}
