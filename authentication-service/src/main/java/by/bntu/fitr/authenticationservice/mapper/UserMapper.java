package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dto.UserCreateDTO;
import by.bntu.fitr.authenticationservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    private final RoleMapper roleMapper;

    @Autowired
    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User toUser(final UserCreateDTO userCreateDTO) {
        return new User(
                userCreateDTO.getFirstName(),
                userCreateDTO.getLastName(),
                userCreateDTO.getUserName(),
                userCreateDTO.getPassword(),
                userCreateDTO.getEmail()
        );
    }
}
