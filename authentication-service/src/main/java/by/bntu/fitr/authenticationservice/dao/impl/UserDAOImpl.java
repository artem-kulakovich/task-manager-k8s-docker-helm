package by.bntu.fitr.authenticationservice.dao.impl;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.dao.RoleDAO;
import by.bntu.fitr.authenticationservice.dao.UserDAO;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.entity.User;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.UserRecord;
import by.bntu.fitr.authenticationservice.handler.DBHandler;
import by.bntu.fitr.authenticationservice.mapper.RoleMapper;
import by.bntu.fitr.authenticationservice.mapper.UserMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.bntu.fitr.authenticationservice.dao.jooq.tables.User.USER;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission.PERMISSION;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.Role.ROLE;
import static by.bntu.fitr.authenticationservice.dao.jooq.tables.RolePermissions.ROLE_PERMISSIONS;


@Component
public class UserDAOImpl implements UserDAO {
    private final DSLContext dslContext;
    private final UserMapper userMapper;
    private final DBHandler dbHandler;

    @Autowired
    public UserDAOImpl(final DSLContext dslContext,
                       final UserMapper userMapper,
                       final DBHandler dbHandler) {
        this.dslContext = dslContext;
        this.userMapper = userMapper;
        this.dbHandler = dbHandler;
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
        dbHandler.userExecuteWithFetchType(dslContext, user, CommonConstant.FetchType.EAGER
                , CommonConstant.InheritLvl.USER_WITH_ROLE_AND_PERMISSION);
        return userMapper.toUser(userRecord);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserById(final Long id, final String fetchType, final int inheritLvl) {
        Record userRecord = dslContext.select()
                .from(USER)
                .where(USER.ID.equal(id))
                .fetchOne();
        User user = userMapper.toUser(userRecord);
        dbHandler.userExecuteWithFetchType(dslContext, user, fetchType, inheritLvl);
        return Optional.ofNullable(user);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserByEmail(final String email, final String fetchType, final int inheritLvl) {
        Record userRecord = dslContext.select()
                .from(USER)
                .where(USER.EMAIL.equal(email))
                .fetchOne();
        User user = userMapper.toUser(userRecord);
        dbHandler.userExecuteWithFetchType(dslContext, user, fetchType, inheritLvl);
        return Optional.ofNullable(user);
    }

    @SuppressWarnings("all")
    @Override
    public Optional<User> findUserByUserName(final String userName, final String fetchType, final int inheritLvl) {
        Record userRecord = dslContext.select()
                .from(USER)
                .where(USER.USER_NAME.equal(userName))
                .fetchOne();
        User user = userMapper.toUser(userRecord);
        dbHandler.userExecuteWithFetchType(dslContext, user, fetchType, inheritLvl);
        return Optional.ofNullable(user);
    }

    @SuppressWarnings("all")
    @Override
    public List<User> finaAllUsers(final String fetchType, final int inheritLvl) {
        List<User> userList = dbHandler.usersExecuteWithFetchType(dslContext, null, CommonConstant.FetchType.EAGER, 2);
        return userList;
    }

    private void delegate(Result<Record> recordResult) {
        Map<Long, Map<Role, Permission>> userRole;
        for (Record record : recordResult) {
            System.out.println(record.getValue(USER.ID));

        }
    }

}
