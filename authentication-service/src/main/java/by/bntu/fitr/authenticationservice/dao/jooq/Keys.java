/*
 * This file is generated by jOOQ.
 */
package by.bntu.fitr.authenticationservice.dao.jooq;


import by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.Role;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.RolePermissions;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.User;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.PermissionRecord;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.RolePermissionsRecord;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.RoleRecord;
import by.bntu.fitr.authenticationservice.dao.jooq.tables.records.UserRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PermissionRecord> PERMISSION_NAME_KEY = Internal.createUniqueKey(Permission.PERMISSION, DSL.name("permission_name_key"), new TableField[] { Permission.PERMISSION.NAME }, true);
    public static final UniqueKey<PermissionRecord> PERMISSION_PKEY = Internal.createUniqueKey(Permission.PERMISSION, DSL.name("permission_pkey"), new TableField[] { Permission.PERMISSION.ID }, true);
    public static final UniqueKey<RoleRecord> ROLE_NAME_KEY = Internal.createUniqueKey(Role.ROLE, DSL.name("role_name_key"), new TableField[] { Role.ROLE.NAME }, true);
    public static final UniqueKey<RoleRecord> ROLE_PKEY = Internal.createUniqueKey(Role.ROLE, DSL.name("role_pkey"), new TableField[] { Role.ROLE.ID }, true);
    public static final UniqueKey<UserRecord> USER_EMAIL_KEY = Internal.createUniqueKey(User.USER, DSL.name("user_email_key"), new TableField[] { User.USER.EMAIL }, true);
    public static final UniqueKey<UserRecord> USER_PKEY = Internal.createUniqueKey(User.USER, DSL.name("user_pkey"), new TableField[] { User.USER.ID }, true);
    public static final UniqueKey<UserRecord> USER_USER_NAME_KEY = Internal.createUniqueKey(User.USER, DSL.name("user_user_name_key"), new TableField[] { User.USER.USER_NAME }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<RolePermissionsRecord, PermissionRecord> ROLE_PERMISSIONS__ROLE_PERMISSIONS_PERMISSION_ID_FKEY = Internal.createForeignKey(RolePermissions.ROLE_PERMISSIONS, DSL.name("role_permissions_permission_id_fkey"), new TableField[] { RolePermissions.ROLE_PERMISSIONS.PERMISSION_ID }, Keys.PERMISSION_PKEY, new TableField[] { Permission.PERMISSION.ID }, true);
    public static final ForeignKey<RolePermissionsRecord, RoleRecord> ROLE_PERMISSIONS__ROLE_PERMISSIONS_ROLE_ID_FKEY = Internal.createForeignKey(RolePermissions.ROLE_PERMISSIONS, DSL.name("role_permissions_role_id_fkey"), new TableField[] { RolePermissions.ROLE_PERMISSIONS.ROLE_ID }, Keys.ROLE_PKEY, new TableField[] { Role.ROLE.ID }, true);
    public static final ForeignKey<UserRecord, RoleRecord> USER__USER_ROLE_ID_FKEY = Internal.createForeignKey(User.USER, DSL.name("user_role_id_fkey"), new TableField[] { User.USER.ROLE_ID }, Keys.ROLE_PKEY, new TableField[] { Role.ROLE.ID }, true);
}