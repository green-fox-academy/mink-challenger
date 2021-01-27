package challenger.mink.commitments;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
public class CommitmentDAO {
  private BigInteger id;
  private String description;
  private Date date;
  private String challengeName;
  private boolean done;
}

//      commitments.id, description, date, challenges.name, done