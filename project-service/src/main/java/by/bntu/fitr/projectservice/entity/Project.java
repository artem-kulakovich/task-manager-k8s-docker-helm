package by.bntu.fitr.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private long id;

    @JsonProperty(value = "name")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @JsonProperty(value = "description")
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @JsonProperty(value = "projectInfoList")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "project")
    private List<ProjectInfo> projectInfoList;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
