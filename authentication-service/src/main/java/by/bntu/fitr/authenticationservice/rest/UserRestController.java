package by.bntu.fitr.authenticationservice.rest;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Autowired
    public UserRestController(final UserService userService, final UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-username")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@RequestParam("userName") final String userName) {
        return new ResponseEntity<>(userMapper.toUserResponseDTO(userService.getUserByUserName(userName)), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam("email") final String email) {
        return new ResponseEntity<>(userMapper.toUserResponseDTO(userService.getUserByEmail(email)), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(userMapper.toUserResponseDTOList(userService.getAllUsers()), HttpStatus.OK);
    }
}
