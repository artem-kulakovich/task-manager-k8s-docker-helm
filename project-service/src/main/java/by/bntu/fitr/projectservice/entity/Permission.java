package by.bntu.fitr.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"permission\"")
public class Permission {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private long id;

    @JsonProperty(value = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "permissionList")
    private List<Role> roleList;

    public Permission(String name) {
        this.name = name;
    }

    public void addRole(Role role) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(role);
    }

    public void addRoles(List<Role> roles) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.addAll(roles);
    }
}
