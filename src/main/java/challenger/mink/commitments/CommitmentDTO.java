package challenger.mink.commitments;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommitmentDTO {
  private String description;
  private LocalDate date;
  private Long challengeId;
}
