package by.bntu.fitr.authenticationservice.rest;

import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.request.UserLoginRequestDTO;
import by.bntu.fitr.authenticationservice.entity.User;
import by.bntu.fitr.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/v1/authentication")
public class AuthenticationRestController {
    private final UserService userService;

    @Autowired
    public AuthenticationRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        return new ResponseEntity<>(userService.login(userLoginRequestDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        return new ResponseEntity<>(userService.registerUser(userCreateRequestDTO), HttpStatus.OK);
    }
}
