package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dto.request.UserCreateRequestDTO;
import by.bntu.fitr.authenticationservice.dto.response.UserResponseDTO;

import lombok.NoArgsConstructor;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.User.USER;


@Component
@NoArgsConstructor
public class UserMapper {

    public User toUser(final UserCreateRequestDTO userCreateRequestDTO) {
        return new User()
                .setUserName(userCreateRequestDTO.getUserName())
                .setFirstName(userCreateRequestDTO.getFirstName())
                .setLastName(userCreateRequestDTO.getLastName())
                .setEmail(userCreateRequestDTO.getEmail())
                .setPassword(userCreateRequestDTO.getPassword());
    }

    public UserResponseDTO toUserResponseDTO(final User user) {
        return user == null
                ? null
                : UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .roleId(user.getRoleId())
                .build();
    }

    public List<UserResponseDTO> toUserResponseDTOList(final List<User> userList) {
        return userList.stream().map(this::toUserResponseDTO).collect(Collectors.toList());
    }

    public User toUser(final Record record) {
        return record == null
                ? null
                : new User(
                record.getValue(USER.ID),
                record.getValue(USER.FIRST_NAME),
                record.getValue(USER.LAST_NAME),
                record.getValue(USER.USER_NAME),
                record.getValue(USER.PASSWORD),
                record.getValue(USER.EMAIL),
                record.getValue(USER.CREATE_AT),
                record.getValue(USER.ROLE_ID)
        );
    }
}
