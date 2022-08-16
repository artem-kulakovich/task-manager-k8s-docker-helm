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
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project_role")
public class ProjectRole {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_role_seq")
    @SequenceGenerator(name = "project_role_seq", sequenceName = "project_role_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private long id;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "project_role_permissions",
            joinColumns = @JoinColumn(name = "project_role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> projectRolePermissionList;

    public ProjectRole(String name) {
        this.name = name;
    }

    public void addPermission(Permission permission) {
        if (projectRolePermissionList == null) {
            projectRolePermissionList = new ArrayList<>();
        }
        projectRolePermissionList.add(permission);
    }

    public void addPermission(List<Permission> permissions) {
        if (projectRolePermissionList == null) {
            projectRolePermissionList = new ArrayList<>();
        }
        projectRolePermissionList.addAll(permissions);
    }
}
