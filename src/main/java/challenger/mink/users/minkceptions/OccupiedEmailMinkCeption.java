package challenger.mink.users.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class OccupiedEmailMinkCeption extends MinkCeption {

  public OccupiedEmailMinkCeption() {
    super(HttpStatus.CONFLICT, "Email already taken.");
  }
}