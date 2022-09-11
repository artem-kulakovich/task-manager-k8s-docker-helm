/*
 * This file is generated by jOOQ.
 */
package by.bntu.fitr.authenticationservice.dao.jooq.tables.entity;


import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private OffsetDateTime createAt;
    private Integer roleId;

    private Role role;

    public User() {
    }

    public User(User value) {
        this.id = value.id;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.userName = value.userName;
        this.password = value.password;
        this.email = value.email;
        this.createAt = value.createAt;
        this.roleId = value.roleId;
    }

    public User(
            Long id,
            String firstName,
            String lastName,
            String userName,
            String password,
            String email,
            OffsetDateTime createAt,
            Integer roleId,
            Role role
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createAt = createAt;
        this.roleId = roleId;
        this.role = role;
    }

    public User(
            Long id,
            String firstName,
            String lastName,
            String userName,
            String password,
            String email,
            OffsetDateTime createAt,
            Integer roleId
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createAt = createAt;
        this.roleId = roleId;
    }

    /**
     * Getter for <code>public.user.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.user.id</code>.
     */
    public User setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.user.first_name</code>.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for <code>public.user.first_name</code>.
     */
    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Getter for <code>public.user.last_name</code>.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for <code>public.user.last_name</code>.
     */
    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Getter for <code>public.user.user_name</code>.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Setter for <code>public.user.user_name</code>.
     */
    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Getter for <code>public.user.password</code>.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for <code>public.user.password</code>.
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Getter for <code>public.user.email</code>.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>public.user.email</code>.
     */
    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Getter for <code>public.user.create_at</code>.
     */
    public OffsetDateTime getCreateAt() {
        return this.createAt;
    }

    /**
     * Setter for <code>public.user.create_at</code>.
     */
    public User setCreateAt(OffsetDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    /**
     * Getter for <code>public.user.role_id</code>.
     */
    public Integer getRoleId() {
        return this.roleId;
    }

    public Role getRole() {
        return this.role;
    }

    /**
     * Setter for <code>public.user.role_id</code>.
     */
    public User setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (createAt == null) {
            if (other.createAt != null)
                return false;
        } else if (!createAt.equals(other.createAt))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.createAt == null) ? 0 : this.createAt.hashCode());
        result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(userName);
        sb.append(", ").append(password);
        sb.append(", ").append(email);
        sb.append(", ").append(createAt);
        sb.append(", ").append(roleId);

        sb.append(")");
        return sb.toString();
    }
}