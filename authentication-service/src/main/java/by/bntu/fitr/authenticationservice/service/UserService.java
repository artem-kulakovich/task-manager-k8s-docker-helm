package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;


import java.util.List;

public interface UserService {
    User getUserByUserName(final String userName);

    User registerUser(final UserCreateRequestDTO userCreateRequestDTO);

    String login(final UserLoginRequestDTO userLoginRequestDTO);

    boolean isUserExists(final String userName);

    User getUserById(final Long id);

    User getUserByEmail(final String email);

    List<User> getAllUsers();

}
