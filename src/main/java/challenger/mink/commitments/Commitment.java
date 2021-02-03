package challenger.mink.commitments;

import challenger.mink.challenges.Challenge;
import challenger.mink.users.User;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "commitments")
public class Commitment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;
  private boolean done;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "challenge_id")
  private Challenge challenge;

  public Commitment(String description, LocalDate date, User user,
                    Challenge challenge) {
    this.description = description;
    this.date = date;
    this.user = user;
    this.challenge = challenge;
  }

  public void setCommitmentDatePlusOneDay() {
    this.date = this.date.plusDays(1);
  }
}