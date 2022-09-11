/*
 * This file is generated by jOOQ.
 */
package by.bntu.fitr.authenticationservice.dao.jooq.tables.records;


import by.bntu.fitr.authenticationservice.dao.jooq.tables.Permission;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PermissionRecord extends UpdatableRecordImpl<PermissionRecord> implements Record3<Long, String, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.permission.id</code>.
     */
    public PermissionRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.permission.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.permission.name</code>.
     */
    public PermissionRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.permission.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.permission.create_at</code>.
     */
    public PermissionRecord setCreateAt(OffsetDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.permission.create_at</code>.
     */
    public OffsetDateTime getCreateAt() {
        return (OffsetDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, OffsetDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, OffsetDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Permission.PERMISSION.ID;
    }

    @Override
    public Field<String> field2() {
        return Permission.PERMISSION.NAME;
    }

    @Override
    public Field<OffsetDateTime> field3() {
        return Permission.PERMISSION.CREATE_AT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public OffsetDateTime component3() {
        return getCreateAt();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public OffsetDateTime value3() {
        return getCreateAt();
    }

    @Override
    public PermissionRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PermissionRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public PermissionRecord value3(OffsetDateTime value) {
        setCreateAt(value);
        return this;
    }

    @Override
    public PermissionRecord values(Long value1, String value2, OffsetDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PermissionRecord
     */
    public PermissionRecord() {
        super(Permission.PERMISSION);
    }

    /**
     * Create a detached, initialised PermissionRecord
     */
    public PermissionRecord(Long id, String name, OffsetDateTime createAt) {
        super(Permission.PERMISSION);

        setId(id);
        setName(name);
        setCreateAt(createAt);
    }
}