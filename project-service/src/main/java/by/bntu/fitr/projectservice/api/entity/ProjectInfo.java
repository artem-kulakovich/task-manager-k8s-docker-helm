package by.bntu.fitr.projectservice.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"project_info\"")
public class ProjectInfo {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_info_seq")
    @SequenceGenerator(name = "project_info_seq", sequenceName = "project_info_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_code_id")
    private Project project;

    public ProjectInfo(Long userId, Role role, Project project) {
        this.userId = userId;
        this.role = role;
        this.project = project;
    }

    public ProjectInfo(Long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
}
