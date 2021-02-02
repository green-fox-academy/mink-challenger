package challenger.mink.commitments.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class IllegalDateMinkCeption
    extends MinkCeption {
  public IllegalDateMinkCeption() {
    super(HttpStatus.BAD_REQUEST, "Illegal date!");
  }
}
