package challenger.mink.commitments.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class InvalidInputCommitmentMinkCeption extends MinkCeption {

  public InvalidInputCommitmentMinkCeption() {
    super(HttpStatus.BAD_REQUEST, "Bad data provided!");
  }
}
