package by.bntu.fitr.authenticationservice.repository;

import by.bntu.fitr.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Deprecated
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findUserByEmail(String email);
}
