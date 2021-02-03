package challenger.mink.commitments.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class InvalidUserMinkCeption
    extends MinkCeption {
  public InvalidUserMinkCeption() {
    super(HttpStatus.UNAUTHORIZED, "Not Your commitment!");
  }
}
