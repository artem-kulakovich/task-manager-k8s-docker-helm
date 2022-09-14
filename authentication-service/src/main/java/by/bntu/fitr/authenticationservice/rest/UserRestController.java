package by.bntu.fitr.authenticationservice.rest;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;
import by.bntu.fitr.authenticationservice.handler.MapperHandler;
import by.bntu.fitr.authenticationservice.model.HttpResponse;
import by.bntu.fitr.authenticationservice.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "user-service", description = "provides operation to work with users ")
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

    @ApiOperation(value = "getUserById", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = UserResponseDTO.class),
            @ApiResponse(code = 404, message = "user not found with this id", response = HttpResponse.class)})
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@ApiParam(value = "id of user to be extract")
                                                       @PathVariable("id") final Long id) {
        User user = userService.getUserById(id, CommonConstant.FetchType.EAGER);
        return new ResponseEntity<>(mapperHandler.executeUserResponseDTOWithInherit(user,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION), HttpStatus.OK);
    }

    @ApiOperation(value = "getUserByUsername", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = UserResponseDTO.class),
            @ApiResponse(code = 404, message = "user not found with this username", response = HttpResponse.class)
    })
    @GetMapping(value = "/find-by-username")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@ApiParam(value = "user name of user to be extract")
                                                             @RequestParam("userName") final String userName) {
        User user = userService.getUserByUserName(userName, CommonConstant.FetchType.EAGER);
        return new ResponseEntity<>(mapperHandler.executeUserResponseDTOWithInherit(user,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION), HttpStatus.OK);
    }

    @ApiOperation(value = "getUserByEmail", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = UserResponseDTO.class),
            @ApiResponse(code = 404, message = "user not found with thus email", response = HttpResponse.class)
    })
    @GetMapping(value = "/find-by-email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@ApiParam(value = "email of user to be extract")
                                                          @RequestParam("email") final String email) {
        User user = userService.getUserByEmail(email, CommonConstant.FetchType.LAZY);
        return new ResponseEntity<>(mapperHandler.executeUserResponseDTOWithInherit(user,
                CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION), HttpStatus.OK);
    }

    @ApiOperation(value = "getAllUsers", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = List.class)
    })
    @GetMapping(value = "/")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> userList = userService.getAllUsers(CommonConstant.FetchType.EAGER);

        List<UserResponseDTO> userResponseDTOList = userList.stream().map((user -> mapperHandler
                        .executeUserResponseDTOWithInherit(user, CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION)))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);
    }
}
