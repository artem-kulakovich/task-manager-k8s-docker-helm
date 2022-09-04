package by.bntu.fitr.authenticationservice.handler;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.PermissionDAO;
import by.bntu.fitr.authenticationservice.dao.RoleDAO;
import by.bntu.fitr.authenticationservice.dao.UserDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.RoleRecord;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.UserRecord;
import by.bntu.fitr.authenticationservice.mapper.PermissionMapper;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.User.USER;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.RolePermissions.ROLE_PERMISSIONS;

@Component
public class DBHandler {
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final PermissionMapper permissionMapper;

    @Autowired
    public DBHandler(final RoleMapper roleMapper,
                     final UserMapper userMapper,
                     final PermissionMapper permissionMapper) {
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
        this.permissionMapper = permissionMapper;
    }

    public void userExecuteWithFetchType(DSLContext dslContext, User user, String fetchType, int inheritLvl) {
        if (fetchType.equals(CommonConstant.FetchType.EAGER) && inheritLvl >= 1 && user != null) {
            Record roleRecord = dslContext.select()
                    .from(ROLE)
                    .where(ROLE.ID.eq(Long.valueOf(user.getRoleId())))
                    .fetchOne();
            Role role = roleMapper.toRole(roleRecord);
            user.setRole(role);
            roleExecuteWithFetchType(dslContext, role, fetchType, --inheritLvl);
        }
    }

    public void roleExecuteWithFetchType(DSLContext dslContext, Role role, String fetchType, int inheritLvl) {
        if (fetchType.equals(CommonConstant.FetchType.EAGER) && inheritLvl >= 1) {
            Result<Record> permissionsRecord = dslContext.select()
                    .from(ROLE_PERMISSIONS)
                    .join(ROLE).on(ROLE.ID.eq((ROLE_PERMISSIONS.ROLE_ID).cast(Long.class)))
                    .join(PERMISSION).on(PERMISSION.ID.eq(ROLE_PERMISSIONS.PERMISSION_ID.cast(Long.class)))
                    .fetch();
            List<Permission> permissionList = permissionsRecord.stream().map(permissionMapper::toPermission)
                    .collect(Collectors.toList());
            role.setPermissionList(permissionList);
        }
    }
}