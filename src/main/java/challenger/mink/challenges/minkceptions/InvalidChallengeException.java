package challenger.mink.challenges.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class InvalidChallengeException
    extends MinkCeption {
  public InvalidChallengeException() {
    super(HttpStatus.BAD_REQUEST, "Invalid challenge data provided!");
  }
}
