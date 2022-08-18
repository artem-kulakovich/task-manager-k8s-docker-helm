package by.bntu.fitr.authenticationservice.mapper;

import by.bntu.fitr.authenticationservice.dto.response.PermissionResponseDTO;
import by.bntu.fitr.authenticationservice.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    public List<PermissionResponseDTO> toPermissionResponseDTOList(List<Permission> permissionList) {
        return permissionList.stream().map(permission -> new PermissionResponseDTO(
                permission.getId(),
                permission.getName()
        )).collect(Collectors.toList());
    }

}
