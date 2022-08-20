package by.bntu.fitr.projectservice.service.impl;

import by.bntu.fitr.projectservice.entity.Permission;
import by.bntu.fitr.projectservice.factory.PermissionFactory;
import by.bntu.fitr.projectservice.repository.PermissionRepository;
import by.bntu.fitr.projectservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionFactory permissionFactory;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository,
                                 PermissionFactory permissionFactory) {
        this.permissionRepository = permissionRepository;
        this.permissionFactory = permissionFactory;
    }

    @Override
    public Permission createPermission(String name) {
        return permissionRepository.save(permissionFactory.getPermission(name));
    }

    @Override
    public boolean isPermissionExists(String name) {
        return permissionRepository.findPermissionByName(name).isPresent();
    }

    @Override
    public Permission getPermissionOrElseNull(String name) {
        return permissionRepository.findPermissionByName(name).orElse(null);
    }


}
