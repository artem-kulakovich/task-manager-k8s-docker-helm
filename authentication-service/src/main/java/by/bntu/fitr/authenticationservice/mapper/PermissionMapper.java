package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dto.response.PermissionResponseDTO;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;

@Component
public class PermissionMapper {

    public List<PermissionResponseDTO> toPermissionResponseDTOList(final List<Permission> permissionList) {
        return null;
    }

    public PermissionResponseDTO toPermissionResponseDTO(final Permission permission) {
        return null;
    }

    public Permission toPermission(final Record record) {
        return record == null
                ? null
                : new Permission(
                record.getValue(PERMISSION.ID),
                record.getValue(PERMISSION.NAME),
                record.getValue(PERMISSION.CREATE_AT)
        );
    }
}
