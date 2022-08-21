package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.constant.ErrorMessageConstant;
import by.bntu.fitr.authenticationservice.constant.PermissionConstant;
import by.bntu.fitr.authenticationservice.constant.RoleConstant;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;
import by.bntu.fitr.authenticationservice.entity.Permission;
import by.bntu.fitr.authenticationservice.entity.Role;
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

import java.util.Collections;
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
    public User getUserByUserName(final String userName) {
        return userRepository.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException(CommonConstant.USER)
        );
    }


    @Override
    public User registerUser(final UserCreateRequestDTO userCreateRequestDTO) {
        if (!userCreateRequestDTO.getPassword().equals(userCreateRequestDTO.getRepeatPassword())) {
            throw new PasswordMismatchException(ErrorMessageConstant.PASSWORD_MISMATCH_EXCEPTION_MSG);
        }

        if (isUserExists(userCreateRequestDTO.getUserName())) {
            throw new UserAlreadyExistsException(ErrorMessageConstant.USER_ALREADY_EXIST_EXCEPTION_MSG);
        }

        User user = userMapper.toUser(userCreateRequestDTO);
        Role role = roleService.createIfNotExists(RoleConstant.USER);
        Permission permission = permissionService.createIfNotExists(PermissionConstant.READ);
        role.setRolePermissionList(Collections.singletonList(permission));
        user.setRole(role);
        user.setPassword(jwtUtil.encodeWithMD5(user.getPassword()));
        return userRepository.save(user);
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
    public boolean isUserExists(final String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    @Override
    public User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(CommonConstant.USER)
        );
    }

    @Override
    public User getUserByEmail(final String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(CommonConstant.USER));
    }
}
