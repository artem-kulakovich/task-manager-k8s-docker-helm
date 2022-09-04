package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;


import java.util.List;

public interface UserService {
    User getUserByUserName(final String userName, final String fetchType, final int inheritLvl);

    User registerUser(final UserCreateRequestDTO userCreateRequestDTO);

    String login(final UserLoginRequestDTO userLoginRequestDTO);

    boolean isUserExists(final String userName);

    User getUserById(final Long id, final String fetchType, final int inheritLvl);

    User getUserByEmail(final String email, final String fetchType, final int inheritLvl);

    List<User> getAllUsers();

}
