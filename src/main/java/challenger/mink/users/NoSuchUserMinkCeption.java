package challenger.mink.users;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class NoSuchUserMinkCeption extends MinkCeption {

  public NoSuchUserMinkCeption() {
    super(HttpStatus.BAD_REQUEST, "No such user!");
  }
}