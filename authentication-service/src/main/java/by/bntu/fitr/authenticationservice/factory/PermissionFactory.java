package by.bntu.fitr.authenticationservice.factory;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionFactory {

    public Permission getPermission(String name) {
        return new Permission().setName(name);
    }
}
