package by.bntu.fitr.projectservice.mapper;

import by.bntu.fitr.projectservice.dto.response.PermissionResponseDTO;
import by.bntu.fitr.projectservice.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    public List<PermissionResponseDTO> toPermissionResponseDTOList(List<Permission> permissionList) {
        return permissionList == null
                ? null
                : permissionList.stream().map(this::toPermissionResponseDTO)
                .collect(Collectors.toList());
    }

    public PermissionResponseDTO toPermissionResponseDTO(Permission permission) {
        return permission == null
                ? null
                : new PermissionResponseDTO(
                permission.getId(),
                permission.getName(),
                permission.getCreateAt()
        );
    }
}
