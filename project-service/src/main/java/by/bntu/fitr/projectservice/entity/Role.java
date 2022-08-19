package by.bntu.fitr.projectservice.entity;

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
@Table(name = "\"role\"")
public class Role {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private long id;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonProperty(value = "permissions")
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

    public Role(String name) {
        this.name = name;
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
}
