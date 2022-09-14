package by.bntu.fitr.authenticationservice.dao.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.RoleDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.RoleRecord;
import by.bntu.fitr.authenticationservice.handler.DBHandler;
import by.bntu.fitr.authenticationservice.mapper.PermissionMapper;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.*;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.User.USER;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.RolePermissions.ROLE_PERMISSIONS;

@Repository
public class RoleDAOImpl extends AbstractDAO<Role>
        implements RoleDAO {
    private final DSLContext dslContext;
    private final RoleMapper roleMapper;

    private final PermissionMapper permissionMapper;

    @Autowired
    public RoleDAOImpl(final DSLContext dslContext,
                       final RoleMapper roleMapper,
                       final PermissionMapper permissionMapper) {
        this.dslContext = dslContext;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public Role save(final Role role) {
        RoleRecord roleRecord = dslContext.insertInto(ROLE)
                .set(ROLE.NAME, role.getName())
                .set(ROLE.CREATE_AT, OffsetDateTime.now())
                .returning(ROLE.ID)
                .fetchOne();
        return roleMapper.toRole(roleRecord);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Role> findRoleById(final Long id, final String fetchType) {
        return doSingleSelectWithFetchType(ROLE.ID.eq(id), fetchType);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Role> findRoleByName(final String name, final String fetchType) {
        return doSingleSelectWithFetchType(ROLE.NAME.eq(name), fetchType);
    }

    @SuppressWarnings("all")
    @Override
    public List<Role> findAllRoles(final String fetchType) {
        return doMultipleSelectWithFetchType(null, fetchType);
    }

    @Override
    public Optional<Role> singleExtractWithFetchType(Result<Record> recordList, String fetchType) {
        if (recordList == null || recordList.isEmpty()) {
            return Optional.ofNullable(null);
        }

        if (fetchType.equals(CommonConstant.FetchType.EAGER)) {
            Role role = roleMapper.toRole(recordList.get(0));
            List<Permission> permissionList = new ArrayList<>();
            permissionList.add(permissionMapper.toPermission(recordList.get(0)));
            role.setPermissionList(permissionList);

            if (recordList.size() > 1) {
                for (int i = 1; i < recordList.size(); i++) {
                    permissionList.add(permissionMapper.toPermission(recordList.get(i)));
                }
            }
            return Optional.ofNullable(role);
        } else {
            return Optional.ofNullable(roleMapper.toRole(recordList.get(0)));
        }
    }

    @Override
    public List<Role> multipleExtractWithFetchType(Result<Record> recordList, String fetchType) {
        List<Role> roleList = new ArrayList<>();
        if (recordList == null || recordList.isEmpty()) {
            return roleList;
        }

        if (fetchType.equals(CommonConstant.FetchType.EAGER)) {
            Map<Long, List<Permission>> rolePermissions = new HashMap<>();

            for (Record record : recordList) {
                Long roleId = record.getValue(ROLE.ID);

                if (rolePermissions.containsKey(roleId)) {
                    Role role = roleMapper.toRole(record);
                    List<Permission> permissionList = role.getPermissionList();
                    permissionList.add(permissionMapper.toPermission(record));
                } else {
                    List<Permission> permissionList = new ArrayList<>();
                    permissionList.add(permissionMapper.toPermission(record));
                    Role role = roleMapper.toRole(record);
                    role.setPermissionList(permissionList);
                    rolePermissions.put(roleId, permissionList);
                    roleList.add(role);
                }
            }
        } else {
            for (Record record : recordList) {
                roleList.add(roleMapper.toRole(record));
            }
        }
        return roleList;
    }

    @Override
    public SelectJoinStep<Record> getSelectJoinStep(String fetchType) {
        switch (fetchType) {
            case CommonConstant.FetchType.EAGER:
                return initSelectRoleJoinStepWithFetchTypeEager();
            case CommonConstant.FetchType.LAZY:
                return initSelectRoleJoinStepWithFetchTypeLazy();
            default:
                throw new UnsupportedOperationException("");
        }
    }

    private SelectJoinStep<Record> initSelectRoleJoinStepWithFetchTypeEager() {
        return dslContext.select()
                .from(ROLE)
                .join(ROLE_PERMISSIONS).on(ROLE.ID.eq(ROLE_PERMISSIONS.ROLE_ID.cast(Long.class)))
                .join(PERMISSION).on(PERMISSION.ID.eq(ROLE_PERMISSIONS.PERMISSION_ID.cast(Long.class)));
    }

    private SelectJoinStep<Record> initSelectRoleJoinStepWithFetchTypeLazy() {
        return dslContext.select()
                .from(ROLE);
    }
}
