package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dto.response.PermissionResponseDTO;
import by.bntu.fitr.authenticationservice.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    public List<PermissionResponseDTO> toPermissionResponseDTOList(final List<Permission> permissionList) {
        return permissionList == null
                ? null
                : permissionList.stream().map(this::toPermissionResponseDTO)
                .collect(Collectors.toList());
    }

    public PermissionResponseDTO toPermissionResponseDTO(final Permission permission) {
        return permission == null
                ? null
                : new PermissionResponseDTO(
                permission.getId(),
                permission.getName(),
                permission.getCreateAt()
        );
    }

}
