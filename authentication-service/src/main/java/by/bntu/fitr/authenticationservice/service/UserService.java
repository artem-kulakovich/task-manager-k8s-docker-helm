package by.bntu.fitr.authenticationservice.service;

import by.bntu.fitr.authenticationservice.dto.UserCreateDTO;
import by.bntu.fitr.authenticationservice.dto.UserLoginDTO;
import by.bntu.fitr.authenticationservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserByUserName(String userName);

    User registerUser(UserCreateDTO userCreateDTO);

    List<User> getAllUsers();

    String login(UserLoginDTO userLoginDTO);

    boolean isUserExists(String userName);

}
