package by.bntu.fitr.authenticationservice.dao.impl;

import by.bntu.fitr.authenticationservice.dao.RoleDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.RoleRecord;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;

@Component
public class RoleDAOImpl implements RoleDAO {
    private final DSLContext dslContext;
    private final RoleMapper roleMapper;

    @Autowired
    private RoleDAOImpl(final DSLContext dslContext,
                        final RoleMapper roleMapper) {
        this.dslContext = dslContext;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role save(Role role) {
        RoleRecord roleRecord = dslContext.insertInto(ROLE)
                .set(ROLE.NAME, role.getName())
                .set(ROLE.CREATE_AT, OffsetDateTime.now())
                .returning(ROLE.ID)
                .fetchOne();
        return roleMapper.toRole(roleRecord);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Role> findRoleById(final Long id) {
        Record record = dslContext.select()
                .from(ROLE)
                .where(ROLE.ID.equal(id))
                .fetchOne();
        return Optional.of(roleMapper.toRole(record));
    }

    @SuppressWarnings("all")
    @Override
    public Optional<Role> findRoleByName(final String name) {
        Record record = dslContext.select()
                .from(ROLE)
                .where(ROLE.NAME.equal(name))
                .fetchOne();
        return Optional.of(roleMapper.toRole(record));
    }

    @SuppressWarnings("all")
    @Override
    public List<Role> findAllRoles() {
        Result<Record> records = dslContext.select()
                .from(ROLE)
                .fetch();
        return records.stream().map(record -> {
            return roleMapper.toRole(record);
        }).collect(Collectors.toList());
    }

}
