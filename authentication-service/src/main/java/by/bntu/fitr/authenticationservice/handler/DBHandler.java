package by.bntu.fitr.authenticationservice.handler;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.mapper.PermissionMapper;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.RolePermissions.ROLE_PERMISSIONS;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.User.USER;

@Deprecated
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
        if (fetchType.equals(CommonConstant.FetchType.EAGER) && inheritLvl >= 1) {
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

    public List<User> usersExecuteWithFetchType(DSLContext dslContext, List<User> userList, String fetchType, int inheritLvl) {
        /*if (fetchType.equals(CommonConstant.FetchType.EAGER) && inheritLvl >= 1) {
            List<Long> userIds = userList.stream().map(User::getId).collect(Collectors.toList());
            Result<Record> roleRecords = dslContext.select()
                    .from(ROLE)
                    .join(USER).on(ROLE.ID.eq(USER.ROLE_ID.cast(Long.class)))
                    .where(USER.ID.in(userIds))
                    .fetch();
            Map<Long, Role> userIdRole = new HashMap<>();
            for (Record record : roleRecords) {
                Long userId = record.getValue(USER.ID);
                if (!userIdRole.containsKey(userId)) {
                    userIdRole.put(userId, roleMapper.toRole(record));
                }
            }

            List<Role> roleList = new ArrayList<>();
            for (User user : userList) {
                Long userId = user.getId();
                if (userIdRole.containsKey(userId)) {
                    user.setRole(userIdRole.get(userId));
                    roleList.add(userIdRole.get(userId));
                }
            }

            rolesExecuteWithFetchType(dslContext, roleList, fetchType, --inheritLvl);



        Result<Record> recordResult = dslContext.select()
                .from(USER)
                .join(ROLE).on(ROLE.ID.eq(USER.ROLE_ID.cast(Long.class)))
                .join(ROLE_PERMISSIONS).on(ROLE.ID.eq(ROLE_PERMISSIONS.ROLE_ID.cast(Long.class)))
                .join(PERMISSION).on(PERMISSION.ID.eq(ROLE_PERMISSIONS.PERMISSION_ID.cast(Long.class)))
                .fetch();

        Map<Long, User> userMap = new HashMap<>();
        List<User> users = new ArrayList<>();
        for (Record record : recordResult) {
            Long userId = record.getValue(USER.ID);

            if (userMap.containsKey(userId)) {
                User user = userMap.get(userId);
                List<Permission> permissionList = user.getRole().getPermissionList();
                permissionList.add(permissionMapper.toPermission(record));
            } else {
                User user = userMapper.toUser(record);
                Role role = roleMapper.toRole(record);
                List<Permission> permissionList = new ArrayList<>();
                permissionList.add(permissionMapper.toPermission(record));
                user.setRole(role);
                role.setPermissionList(permissionList);
                userMap.put(userId, user);
                users.add(user);
            }
        }

         */
        return null;
    }

    public void rolesExecuteWithFetchType(DSLContext dslContext, List<Role> roleList, String fetchType, int inheritLvl) {
        if (fetchType.equals(CommonConstant.FetchType.EAGER) && inheritLvl >= 1) {
            List<Long> roleIds = roleList.stream().map(Role::getId).collect(Collectors.toList());
            Result<Record> permissionsRecord = dslContext.select()
                    .from(ROLE_PERMISSIONS)
                    .join(ROLE).on(ROLE.ID.eq((ROLE_PERMISSIONS.ROLE_ID).cast(Long.class)))
                    .join(PERMISSION).on(PERMISSION.ID.eq(ROLE_PERMISSIONS.PERMISSION_ID.cast(Long.class)))
                    .where(ROLE.ID.in(roleIds))
                    .fetch();
            Map<Long, List<Permission>> rolePermissions = new HashMap<>();
            for (Record record : permissionsRecord) {
                Long roleId = record.getValue(ROLE.ID);
                if (rolePermissions.containsKey(roleId)) {
                    List<Permission> permissionList = rolePermissions.get(roleId);
                    permissionList.add(permissionMapper.toPermission(record));
                } else {
                    List<Permission> permissionList = new ArrayList<>();
                    permissionList.add(permissionMapper.toPermission(record));
                    rolePermissions.put(roleId, permissionList);
                }
            }

            for (Role role : roleList) {
                Long roleId = role.getId();
                if (rolePermissions.containsKey(roleId)) {
                    role.setPermissionList(rolePermissions.get(roleId));
                }
            }
        }
    }
}