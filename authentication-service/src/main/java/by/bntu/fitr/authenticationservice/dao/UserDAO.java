package by.bntu.fitr.authenticationservice.dao;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User save(final User user);

    Optional<User> findUserById(final Long id, final String fetchType);

    Optional<User> findUserByEmail(final String email, final String fetchType);

    Optional<User> findUserByUserName(final String userName, final String fetchType);

    List<User> finaAllUsers(final String fetchType);

}
