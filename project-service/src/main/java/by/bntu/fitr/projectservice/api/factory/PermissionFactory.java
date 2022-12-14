package by.bntu.fitr.projectservice.api.factory;

import by.bntu.fitr.projectservice.api.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionFactory {

    public Permission getPermission(String name) {
        return new Permission(
                name
        );
    }
}
