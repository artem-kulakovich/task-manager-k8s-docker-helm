package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.entity.User;

import java.util.List;

public interface UserService {
    User getUserByUserName(final String userName);

    User registerUser(final UserCreateRequestDTO userCreateRequestDTO);

    String login(final UserLoginRequestDTO userLoginRequestDTO);

    boolean isUserExists(final String userName);

    User getUserById(Long id);

}
