package challenger.mink.challenges;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChallengeDAO {
  private BigInteger id;
  private String name;
  private Date startDate;
  private Date endDate;
  private BigInteger minimumCommitment;

  //  public ChallengeDAO(BigInteger id, String name, Date startDate, Date endDate,
//                      BigInteger minimumCommitment) {
//    this.id = id;
//    this.name = name;
//    this.startDate = startDate;
//    this.endDate = endDate;
//    this.minimumCommitment = minimumCommitment;
//  }
  public ChallengeDAO(Object[] tuple) {
    this.id = (BigInteger) tuple[0];
    this.name = (String) tuple[1];
    this.startDate = (Date) tuple[2];
    this.endDate = (Date) tuple[3];
    this.minimumCommitment = (BigInteger) tuple[4];
  }
}
