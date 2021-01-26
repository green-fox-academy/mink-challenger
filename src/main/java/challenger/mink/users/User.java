package challenger.mink.users;

import challenger.mink.commitments.Commitment;
import challenger.mink.users.roles.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private String email;
  @OneToMany(mappedBy = "user")
  private List<Commitment> commitments;
  private String uuid;
  @Column(name = "email_verified")
  private boolean emailVerified;
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  protected Set<Role> roles = new HashSet<>();

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.emailVerified = false;
    this.roles.add(new Role());
  }
}