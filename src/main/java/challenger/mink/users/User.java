package challenger.mink.users;

import challenger.mink.challenges.Challenge;
import challenger.mink.commitments.Commitment;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
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

}