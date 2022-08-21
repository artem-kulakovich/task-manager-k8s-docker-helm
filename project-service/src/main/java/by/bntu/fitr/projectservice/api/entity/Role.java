package by.bntu.fitr.projectservice.api.entity;

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

    @Column(name = "createAt")
    private Date createAt;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissionList;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "role")
    private List<ProjectInfo> projectInfoList;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Project project) {
        this.name = name;
        this.project = project;
    }

    public void addPermission(Permission permission) {
        if (permissionList == null) {
            permissionList = new ArrayList<>();
        }
        permissionList.add(permission);
    }

    public void addPermission(List<Permission> permissions) {
        if (permissionList == null) {
            permissionList = new ArrayList<>();
        }
        permissionList.addAll(permissions);
    }

    @PrePersist
    public void createDate() {
        createAt = new Date();
    }
}
