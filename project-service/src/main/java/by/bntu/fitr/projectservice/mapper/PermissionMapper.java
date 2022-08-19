package by.bntu.fitr.projectservice.mapper;

import by.bntu.fitr.projectservice.dto.response.PermissionResponseDTO;
import by.bntu.fitr.projectservice.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    public List<PermissionResponseDTO> toPermissionResponseDTO(List<Permission> permissionList) {
        return permissionList == null
                ? null
                : permissionList.stream().map(permission -> new PermissionResponseDTO(
                permission.getId(),
                permission.getName()
        )).collect(Collectors.toList());
    }
}
