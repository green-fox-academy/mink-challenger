package challenger.mink.users.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class OccupiedUsernameMinkCeption extends MinkCeption {

  public OccupiedUsernameMinkCeption() {
    super(HttpStatus.CONFLICT, "Username already taken, please choose an other one.");
  }
}
