package challenger.mink.challenges;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class NoSuchChallengeMinkCeption extends MinkCeption {

  public NoSuchChallengeMinkCeption() {
    super(HttpStatus.BAD_REQUEST, "No such challenge!");
  }
}
