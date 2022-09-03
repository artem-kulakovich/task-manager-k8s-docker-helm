package by.bntu.fitr.authenticationservice.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Deprecated
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"permission\"")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
    @SequenceGenerator(name = "permission_seq", sequenceName = "permission_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "create_at")
    private Date createAt;

    public Permission(String name) {
        this.name = name;
    }

    @PrePersist
    public void createDate() {
        createAt = new Date();
    }

}
