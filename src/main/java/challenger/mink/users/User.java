package challenger.mink.users;

import challenger.mink.challenges.Challenge;
import challenger.mink.commitments.Commitment;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private String email;
  @OneToMany(mappedBy = "user")
  private List<Commitment> commitmentList;
  @ManyToOne
  private Challenge challenge;
  private String uuid;
  @Column(name = "email_verified")
  private boolean emailVerified;

  public User(){
    this.emailVerified = false;
  }
  private UserType userType;

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.userType = UserType.USER;
  }
}