package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.ErrorMessageConstant;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.entity.User;
import by.bntu.fitr.authenticationservice.exception.LoginException;
import by.bntu.fitr.authenticationservice.exception.PasswordMismatchException;
import by.bntu.fitr.authenticationservice.exception.UserAlreadyExistsException;
import by.bntu.fitr.authenticationservice.exception.UserNotFoundException;
import by.bntu.fitr.authenticationservice.jwt.JWTUtil;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import by.bntu.fitr.authenticationservice.repository.UserRepository;
import by.bntu.fitr.authenticationservice.jwt.JWTTokenProvider;
import by.bntu.fitr.authenticationservice.service.PermissionService;
import by.bntu.fitr.authenticationservice.service.RoleService;
import by.bntu.fitr.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PermissionService permissionService;
    private final UserMapper userMapper;
    private final JWTTokenProvider jwtTokenProvider;

    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           JWTUtil jwtUtil,
                           PermissionService permissionService,
                           UserMapper userMapper,
                           JWTTokenProvider jwtTokenProvider,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.permissionService = permissionService;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleService = roleService;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException("User")
        );
    }

    @Transactional
    @Override
    public UserResponseDTO registerUser(final UserCreateRequestDTO userCreateRequestDTO) {
        if (!userCreateRequestDTO.getPassword().equals(userCreateRequestDTO.getRepeatPassword())) {
            throw new PasswordMismatchException(ErrorMessageConstant.PASSWORD_MISMATCH_EXCEPTION_MSG);
        }

        if (isUserExists(userCreateRequestDTO.getUserName())) {
            throw new UserAlreadyExistsException(ErrorMessageConstant.USER_ALREADY_EXIST_EXCEPTION_MSG);
        }

        User user = userMapper.toUser(userCreateRequestDTO);
        user.setPassword(jwtUtil.encodeWithMD5(user.getPassword()));

        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String login(final UserLoginRequestDTO userLoginRequestDTO) {
        try {
            User user = getUserByUserName(userLoginRequestDTO.getUserName());
            return jwtTokenProvider.createToken(user.getId(),
                    user.getUserName(),
                    user.getEmail(),
                    roleService.getRoleName(user.getRole()),
                    permissionService.getRolePermissionsName(user.getRole())
            );
        } catch (UserNotFoundException e) {
            throw new LoginException(ErrorMessageConstant.CANT_LOGIN_EXCEPTION_MSG);
        }
    }

    @Override
    public boolean isUserExists(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }
}
