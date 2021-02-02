package challenger.mink.commitments.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class ChallengeNameDuplicationMinkCeption
    extends MinkCeption {

  public ChallengeNameDuplicationMinkCeption() {
    super(HttpStatus.FORBIDDEN, "Cannot add more then 1 challenges w/ the same name!");
  }
}
