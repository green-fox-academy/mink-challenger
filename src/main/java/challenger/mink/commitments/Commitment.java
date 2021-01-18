package challenger.mink.commitments;

import challenger.mink.challenges.Challenge;
import challenger.mink.users.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Commitment {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private long id;
  private String description;
  private LocalDate date;
  private boolean done;
  @ManyToOne
  private User user;
  @ManyToOne
  private Challenge challenge;

  public Commitment(String description, LocalDate date, User user,
                    Challenge challenge) {
    this.description = description;
    this.date = date;
    this.user = user;
    this.challenge = challenge;
  }
}