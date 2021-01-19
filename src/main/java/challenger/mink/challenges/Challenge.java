package challenger.mink.challenges;

import challenger.mink.commitments.Commitment;
import challenger.mink.users.User;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Challenge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
  private int minimumCommitment;
  @OneToMany(mappedBy = "challenge")
  private List<Commitment> commitmentList;
  @OneToMany(mappedBy = "challenge")
  private List<User> userList;

  public Challenge(String name, LocalDate startDate, LocalDate endDate, int minimumCommitment) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.minimumCommitment = minimumCommitment;
  }
}