package by.bntu.fitr.authenticationservice.dao.impl;

import by.bntu.fitr.authenticationservice.dao.PermissionDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.PermissionRecord;
import by.bntu.fitr.authenticationservice.mapper.PermissionMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;

@Component
public class PermissionDAOImpl implements PermissionDAO {
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
    public Permission save(Permission permission) {
        PermissionRecord permissionRecord = dslContext.insertInto(PERMISSION)
                .set(PERMISSION.NAME, permission.getName())
                .set(PERMISSION.CREATE_AT, OffsetDateTime.now())
                .returning(PERMISSION.ID)
                .fetchOne();
        return permissionMapper.toPermission(permissionRecord);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Permission> findPermissionById(final Long id) {
        Record record = dslContext.select()
                .from(PERMISSION)
                .where(PERMISSION.ID.equal(id))
                .fetchOne();
        return Optional.of(permissionMapper.toPermission(record));
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Permission> findPermissionByName(final String name) {
        Record record = dslContext.select()
                .from(PERMISSION)
                .where(PERMISSION.NAME.equal(name))
                .fetchOne();
        return Optional.of(permissionMapper.toPermission(record));
    }

    @SuppressWarnings("all")
    @Override
    public List<Permission> findAllPermissions() {
        Result<Record> records = dslContext.select()
                .from(PERMISSION)
                .fetch();
        return records.stream().map((record -> {
            return permissionMapper.toPermission(record);
        })).collect(Collectors.toList());
    }


}
