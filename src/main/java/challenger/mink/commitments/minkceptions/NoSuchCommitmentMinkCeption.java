package challenger.mink.commitments.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class NoSuchCommitmentMinkCeption extends MinkCeption {

  public NoSuchCommitmentMinkCeption() {
    super(HttpStatus.BAD_REQUEST, "No such commitment!");
  }
}
