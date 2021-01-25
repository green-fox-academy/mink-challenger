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
import org.springframework.format.annotation.DateTimeFormat;

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
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  private long minimumCommitment;
  @OneToMany(mappedBy = "challenge")
  private List<Commitment> commitmentList;
  @OneToMany(mappedBy = "challenge", cascade = javax.persistence.CascadeType.PERSIST)
  private List<User> userList;

  public Challenge(String name, LocalDate startDate, LocalDate endDate, Long minimumCommitment) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.minimumCommitment = minimumCommitment;
  }
}