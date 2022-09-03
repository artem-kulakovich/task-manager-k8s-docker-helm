package by.bntu.fitr.authenticationservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Deprecated
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"role\"")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "create_at")
    private Date createAt;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> rolePermissionList;


    public Role(String name) {
        this.name = name;
    }

    public void addPermission(Permission permission) {
        if (rolePermissionList == null) {
            rolePermissionList = new ArrayList<>();
        }
        rolePermissionList.add(permission);
    }

    public void addPermission(List<Permission> permissions) {
        if (rolePermissionList == null) {
            rolePermissionList = new ArrayList<>();
        }
        rolePermissionList.addAll(permissions);
    }

    @PrePersist
    public void createDate() {
        createAt = new Date();
    }
}
