package by.bntu.fitr.authenticationservice.dao.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.RoleDAO;
import by.bntu.fitr.authenticationservice.dao.UserDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.UserRecord;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.User.USER;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;


@Component
public class UserDAOImpl implements UserDAO {
    private final DSLContext dslContext;
    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    @Autowired
    public UserDAOImpl(final DSLContext dslContext,
                       final UserMapper userMapper,
                       final RoleMapper roleMapper) {
        this.dslContext = dslContext;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
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
                .returning(USER.ID)
                .fetchOne();
        return getUserEntity(userRecord);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserById(final Long id, String fetchType) {
        Record userRecord = dslContext.select()
                .from(USER)
                .where(USER.ID.equal(id))
                .fetchOne();
        User user = userMapper.toUser(userRecord);
        if (fetchType.equals(CommonConstant.FETCH_TYPE_EAGER)) {
            Record roleRecord = dslContext.select()
                    .from(ROLE)
                    .where(ROLE.ID.eq(Long.valueOf(userRecord.getValue(USER.ROLE_ID))))
                    .fetchOne();
            user.setRole(roleMapper.toRole(roleRecord));
        }
        return Optional.of(user);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserByEmail(final String email) {
        Record record = dslContext.select()
                .from(USER)
                .where(USER.EMAIL.equal(email))
                .fetchOne();
        return Optional.of(getUserEntity(record));
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserByUserName(final String userName) {
        Record record = dslContext.select()
                .from(USER)
                .where(USER.USER_NAME.equal(userName))
                .fetchOne();
        return Optional.of(getUserEntity(record));
    }

    @SuppressWarnings("all")
    @Override
    public List<User> finaAllUsers() {
        Result<Record> records = dslContext.select()
                .from(USER)
                .fetch();
        return records.stream().map(this::getUserEntity).collect(Collectors.toList());
    }


    private User getUserEntity(final Record r) {
        return r == null
                ? null
                : new User(
                r.getValue(USER.ID),
                r.getValue(USER.FIRST_NAME),
                r.getValue(USER.LAST_NAME),
                r.getValue(USER.USER_NAME),
                r.getValue(USER.PASSWORD),
                r.getValue(USER.EMAIL),
                r.getValue(USER.CREATE_AT),
                r.getValue(USER.ROLE_ID),
                null
        );
    }
}
