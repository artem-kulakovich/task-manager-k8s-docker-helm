package by.bntu.fitr.authenticationservice.rest;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.handler.MapperHandler;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import by.bntu.fitr.authenticationservice.repository.UserRepository;
import by.bntu.fitr.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authentication-service/users")
public class UserRestController {
    private final UserService userService;
    private final MapperHandler mapperHandler;

    @Autowired
    public UserRestController(final UserService userService,
                              final MapperHandler mapperHandler) {
        this.userService = userService;
        this.mapperHandler = mapperHandler;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") final Long id) {
        User user = userService.getUserById(id,
                CommonConstant.FetchType.EAGER,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION);
        return new ResponseEntity<>(mapperHandler.executeUserResponseDTOWithInherit(user,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-username")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@RequestParam("userName") final String userName) {
        User user = userService.getUserByUserName(userName,
                CommonConstant.FetchType.EAGER,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION);
        return new ResponseEntity<>(mapperHandler.executeUserResponseDTOWithInherit(user,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam("email") final String email) {
        User user = userService.getUserByEmail(email,
                CommonConstant.FetchType.LAZY,
                CommonConstant.InheritLvl.USER_WITH_NONE);
        return new ResponseEntity<>(mapperHandler.executeUserResponseDTOWithInherit(user,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
