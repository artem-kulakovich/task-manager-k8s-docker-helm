package by.bntu.fitr.authenticationservice.dao.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.PermissionDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.PermissionRecord;
import by.bntu.fitr.authenticationservice.mapper.PermissionMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;

@Component
public class PermissionDAOImpl extends AbstractDAO<Permission>
        implements PermissionDAO {
    private final DSLContext dslContext;
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionDAOImpl(final DSLContext dslContext,
                             final PermissionMapper permissionMapper) {
        this.dslContext = dslContext;
        this.permissionMapper = permissionMapper;
    }

    @SuppressWarnings("all")
    @Override
    public Permission save(final Permission permission) {
        PermissionRecord permissionRecord = dslContext.insertInto(PERMISSION)
                .set(PERMISSION.NAME, permission.getName())
                .set(PERMISSION.CREATE_AT, OffsetDateTime.now())
                .returning(PERMISSION.ID, PERMISSION.NAME, PERMISSION.CREATE_AT)
                .fetchOne();
        return permissionMapper.toPermission(permissionRecord);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Permission> findPermissionById(final Long id, final String fetchType) {
        return doSingleSelectWithFetchType(PERMISSION.ID.eq(id), fetchType);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Permission> findPermissionByName(final String name, final String fetchType) {
        return doSingleSelectWithFetchType(PERMISSION.NAME.eq(name), fetchType);
    }

    @SuppressWarnings("all")
    @Override
    public List<Permission> findAllPermissions(final String fetchType) {
        return doMultipleSelectWithFetchType(null, fetchType);
    }


    @Override
    public Optional<Permission> singleExtractWithFetchType(Result<Record> recordList, String fetchType) {
        if (recordList == null || recordList.isEmpty()) {
            return Optional.ofNullable(null);
        }

        return Optional.ofNullable(permissionMapper.toPermission(recordList.get(0)));
    }

    @Override
    public List<Permission> multipleExtractWithFetchType(Result<Record> recordList, String fetchType) {
        List<Permission> permissionList = new ArrayList<>();
        if (recordList == null || recordList.isEmpty()) {
            return permissionList;
        }

        for (Record record : recordList) {
            permissionList.add(permissionMapper.toPermission(record));
        }
        return permissionList;
    }

    @Override
    public SelectJoinStep<Record> getSelectJoinStep(String fetchType) {
        switch (fetchType) {
            case CommonConstant.FetchType.EAGER:
                return null;
            case CommonConstant.FetchType.LAZY:
                return initSelectUserJoinStepWithFetchTypeLazy();
        }
        return initSelectUserJoinStepWithFetchTypeLazy();
    }

    private SelectJoinStep<Record> initSelectUserJoinStepWithFetchTypeLazy() {
        return dslContext.select()
                .from(PERMISSION);
    }
}
