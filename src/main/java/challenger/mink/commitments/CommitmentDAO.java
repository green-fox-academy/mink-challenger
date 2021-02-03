package challenger.mink.commitments;

import java.math.BigInteger;
import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommitmentDAO {
  private BigInteger id;
  private String description;
  private Date date;
  private String challengeName;
  private Boolean done;

  public CommitmentDAO(BigInteger id, String description, Date date, String challengeName,
                       Boolean done) {
    this.id = id;
    this.description = description;
    this.date = date;
    this.challengeName = challengeName;
    this.done = done;
  }

  public CommitmentDAO(Object[] tuple) {
    this.id = (BigInteger) tuple[0];
    this.description = (String) tuple[1];
    this.date = (Date) tuple[2];
    this.challengeName = (String) tuple[3];
    this.done = (Boolean) tuple[4];
  }
}

//      commitments.id, description, date, challenges.name, done