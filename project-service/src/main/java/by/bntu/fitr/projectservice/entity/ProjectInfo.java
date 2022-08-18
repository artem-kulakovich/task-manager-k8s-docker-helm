package by.bntu.fitr.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long id;

    @Column(name = "user_id")
    private long userId;

    @JsonProperty(value = "project")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_code_id")
    private Project project;

}
