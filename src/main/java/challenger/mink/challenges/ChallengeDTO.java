package challenger.mink.challenges;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeDTO {
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
  private long minimumCommitment;

  public ChallengeDTO(String name, LocalDate startDate, LocalDate endDate, int minimumCommitment) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.minimumCommitment = minimumCommitment;
  }
}
