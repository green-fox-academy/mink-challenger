package challenger.mink.commitments;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
public class CommitmentDTO {
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;
  private Long challengeId;

  public CommitmentDTO(String description, LocalDate date, Long challengeId) {
    this.description = description;
    this.date = date;
    this.challengeId = challengeId;
  }
}
