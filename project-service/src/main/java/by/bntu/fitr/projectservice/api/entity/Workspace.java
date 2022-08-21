package by.bntu.fitr.projectservice.api.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "workspace")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workspace_seq")
    @SequenceGenerator(name = "workspace_seq", sequenceName = "workspace_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "createAt")
    private Date createAt;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "workspace")
    private List<Project> projectList;


    public Workspace(String name, long userId) {
        this.name = name;
        this.userId = userId;
    }

    public Workspace(String name) {
        this.name = name;
    }

    public void addProjectToWorkspace(Project project) {
        if (projectList == null) {
            projectList = new ArrayList<>();
        }
        projectList.add(project);
    }

    @PrePersist
    public void createDate() {
        createAt = new Date();
    }
}
