package by.bntu.fitr.authenticationservice.entity;

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
@Table(name = "\"user\"")
public class User {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private long id;

    @JsonProperty(value = "firstName")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty(value = "lastName")
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty(value = "userName")
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @JsonProperty(value = "email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonProperty(value = "password")
    @Column(name = "password", nullable = false)
    private String password;

    @JsonProperty(value = "role")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Role role;

    public User(String firstName, String lastName, String userName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
