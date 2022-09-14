package by.bntu.fitr.authenticationservice.service.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.constant.ErrorMessageConstant;
import by.bntu.fitr.authenticationservice.constant.PermissionConstant;
import by.bntu.fitr.authenticationservice.constant.RoleConstant;
import by.bntu.fitr.authenticationservice.dao.UserDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;
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
    private final JWTUtil jwtUtil;
    private final PermissionService permissionService;
    private final UserMapper userMapper;
    private final JWTTokenProvider jwtTokenProvider;

    private final RoleService roleService;

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(final JWTUtil jwtUtil,
                           final PermissionService permissionService,
                           final UserMapper userMapper,
                           final JWTTokenProvider jwtTokenProvider,
                           final RoleService roleService,
                           final UserDAO userDAO) {
        this.jwtUtil = jwtUtil;
        this.permissionService = permissionService;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleService = roleService;
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByUserName(final String userName, final String fetchType) {
        return userDAO.findUserByUserName(userName, fetchType).orElseThrow(
                () -> new UserNotFoundException(CommonConstant.USER)
        );
    }

    @Transactional
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
        role.setPermissionList(Collections.singletonList(permission));
        user.setRoleId(role.getId().intValue());
        user.setPassword(jwtUtil.encodeWithMD5(user.getPassword()));
        return userDAO.save(user).setRole(role);
    }

    @Override
    public String login(final UserLoginRequestDTO userLoginRequestDTO) {

        try {
            User user = getUserByUserName(userLoginRequestDTO.getUserName(), CommonConstant.FetchType.EAGER);
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
        return userDAO.findUserByUserName(userName, CommonConstant.FetchType.LAZY).isPresent();
    }

    @Override
    public User getUserById(final Long id, final String fetchType) {
        return userDAO.findUserById(id, fetchType).orElseThrow(
                () -> new UserNotFoundException(CommonConstant.USER)
        );
    }

    @Override
    public User getUserByEmail(final String email, final String fetchType) {
        return userDAO.findUserByEmail(email, fetchType)
                .orElseThrow(() -> new UserNotFoundException(CommonConstant.USER));
    }

    @Override
    public List<User> getAllUsers(final String fetchType) {
        return userDAO.finaAllUsers(fetchType);
    }
}
