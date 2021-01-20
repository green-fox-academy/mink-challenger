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
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "challenges")
public class Challenge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
  @OneToMany(mappedBy = "challenge")
  private List<Commitment> commitmentList;
  @OneToMany(mappedBy = "challenge")
  private List<User> userList;

  public Challenge(String name, LocalDate startDate, LocalDate endDate) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}