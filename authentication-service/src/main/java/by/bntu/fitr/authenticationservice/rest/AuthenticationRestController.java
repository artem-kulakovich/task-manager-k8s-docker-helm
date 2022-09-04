package by.bntu.fitr.authenticationservice.rest;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.handler.MapperHandler;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import by.bntu.fitr.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/v1/authentication-service/authentication")
public class AuthenticationRestController {
    private final UserService userService;
    private final MapperHandler mapperHandler;

    @Autowired
    public AuthenticationRestController(final UserService userService,
                                        final MapperHandler mapperHandler) {
        this.userService = userService;
        this.mapperHandler = mapperHandler;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid final UserLoginRequestDTO userLoginRequestDTO) {
        return new ResponseEntity<>(userService.login(userLoginRequestDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid final UserCreateRequestDTO userCreateRequestDTO) {
        User user = userService.registerUser(userCreateRequestDTO);
        return new ResponseEntity<>(mapperHandler.executeUserResponseDTOWithInherit(user,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION), HttpStatus.OK);
    }
}
