package by.bntu.fitr.authenticationservice.dao.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.UserDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.UserRecord;
import by.bntu.fitr.authenticationservice.mapper.PermissionMapper;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.*;


import static by.bntu.fitr.authenticationservice.dao.jooq.tables.User.USER;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.RolePermissions.ROLE_PERMISSIONS;


@Repository
public class UserDAOImpl extends AbstractDAO<User>
        implements UserDAO {
    private final DSLContext dslContext;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    @Autowired
    public UserDAOImpl(final DSLContext dslContext,
                       final UserMapper userMapper,
                       final RoleMapper roleMapper,
                       final PermissionMapper permissionMapper) {
        this.dslContext = dslContext;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    @SuppressWarnings("all")
    @Override
    public User save(final User user) {
        UserRecord userRecord = dslContext.insertInto(USER)
                .set(USER.USER_NAME, user.getUserName())
                .set(USER.FIRST_NAME, user.getFirstName())
                .set(USER.LAST_NAME, user.getLastName())
                .set(USER.EMAIL, user.getEmail())
                .set(USER.PASSWORD, user.getPassword())
                .set(USER.CREATE_AT, OffsetDateTime.now())
                .set(USER.ROLE_ID, user.getRoleId())
                .returning(USER.ID, USER.USER_NAME, USER.LAST_NAME, USER.EMAIL,
                        USER.PASSWORD, USER.FIRST_NAME, USER.CREATE_AT, USER.ROLE_ID)
                .fetchOne();
        return userMapper.toUser(userRecord);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserById(final Long id, final String fetchType) {
        return doSingleSelectWithFetchType(USER.ID.eq(id), CommonConstant.FetchType.EAGER);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserByEmail(final String email, final String fetchType) {
        return doSingleSelectWithFetchType(USER.EMAIL.eq(email), fetchType);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserByUserName(final String userName, final String fetchType) {
        return doSingleSelectWithFetchType(USER.USER_NAME.eq(userName), fetchType);
    }

    @SuppressWarnings("all")
    @Override
    public List<User> finaAllUsers(final String fetchType) {
        return doMultipleSelectWithFetchType(null, fetchType);
    }

    @Override
    public Optional<User> singleExtractWithFetchType(final Result<Record> recordList, final String fetchType) {
        if (recordList == null || recordList.isEmpty()) {
            return Optional.ofNullable(null);
        }

        if (fetchType.equals(CommonConstant.FetchType.EAGER)) {

            List<Permission> permissionList = new ArrayList<>();
            User user = userMapper.toUser(recordList.get(0));
            user.setRole(roleMapper.toRole(recordList.get(0)));
            permissionList.add(permissionMapper.toPermission(recordList.get(0)));
            user.getRole().setPermissionList(permissionList);

            if (recordList.size() > 1) {
                for (int i = 1; i < recordList.size(); i++) {
                    permissionList.add(permissionMapper.toPermission(recordList.get(i)));
                }
            }
            return Optional.ofNullable(user);
        } else {
            return Optional.ofNullable(userMapper.toUser(recordList.get(0)));
        }
    }

    @Override
    public List<User> multipleExtractWithFetchType(final Result<Record> recordList, final String fetchType) {
        List<User> userList = new ArrayList<>();
        if (recordList == null || recordList.isEmpty()) {
            return userList;
        }

        if (fetchType.equals(CommonConstant.FetchType.EAGER)) {
            Map<Long, User> userMap = new HashMap<>();
            for (Record record : recordList) {
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
                    userList.add(user);
                }
            }
        } else {
            for (Record record : recordList) {
                userList.add(userMapper.toUser(record));
            }
        }
        return userList;
    }

    @Override
    public SelectJoinStep<Record> getSelectJoinStep(final String fetchType) {
        switch (fetchType) {
            case CommonConstant.FetchType.EAGER:
                return initSelectUserJoinStepWithFetchTypeEager();
            case CommonConstant.FetchType.LAZY:
                return initSelectUserJoinStepWithFetchTypeLazy();
            default:
                throw new UnsupportedOperationException("");
        }
    }

    private SelectJoinStep<Record> initSelectUserJoinStepWithFetchTypeEager() {
        return dslContext.select()
                .from(USER)
                .join(ROLE).on(ROLE.ID.eq(USER.ROLE_ID.cast(Long.class)))
                .join(ROLE_PERMISSIONS).on(ROLE.ID.eq(ROLE_PERMISSIONS.ROLE_ID.cast(Long.class)))
                .join(PERMISSION).on(PERMISSION.ID.eq(ROLE_PERMISSIONS.PERMISSION_ID.cast(Long.class)));
    }

    private SelectJoinStep<Record> initSelectUserJoinStepWithFetchTypeLazy() {
        return dslContext.select()
                .from(USER);
    }
}
