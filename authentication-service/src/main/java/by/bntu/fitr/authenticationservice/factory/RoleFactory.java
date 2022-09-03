package by.bntu.fitr.authenticationservice.factory;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {

    public Role getRole(String name) {
        return new Role().setName(name);
    }
}
