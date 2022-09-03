package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dto.response.RoleResponseDTO;

import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;


@Component
public class RoleMapper {
    private final PermissionMapper permissionMapper;

    @Autowired
    public RoleMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public RoleResponseDTO toRoleResponseDTO(final Role role) {
        return null;
    }


    public Role toRole(final Record record) {
        return record == null
                ? null
                : new Role(
                record.getValue(ROLE.ID),
                record.getValue(ROLE.NAME),
                record.getValue(ROLE.CREATE_AT)
        );
    }
}
