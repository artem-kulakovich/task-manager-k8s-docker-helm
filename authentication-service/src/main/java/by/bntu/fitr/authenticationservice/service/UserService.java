package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;
import by.bntu.fitr.authenticationservice.entity.User;

import java.util.List;

public interface UserService {
    User getUserByUserName(String userName);

    User registerUser(UserCreateRequestDTO userCreateRequestDTO);

    List<User> getAllUsers();

    String login(UserLoginRequestDTO userLoginRequestDTO);

    boolean isUserExists(String userName);

}
