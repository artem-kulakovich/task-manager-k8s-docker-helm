package by.bntu.fitr.projectservice.api.entity;

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

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"project\"")
public class Project {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createAt")
    private Date createAt;


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "project")
    private List<ProjectInfo> projectInfoList;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "project")
    private List<Role> roleList;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description, List<ProjectInfo> projectInfoList) {
        this.name = name;
        this.description = description;
        this.projectInfoList = projectInfoList;
    }

    public void addRoleToProject(Role role) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(role);
    }

    @PrePersist
    public void createDate() {
        createAt = new Date();
    }
}
