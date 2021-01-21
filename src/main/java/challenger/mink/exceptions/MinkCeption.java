package challenger.mink.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class MinkCeption extends Exception {
  private final HttpStatus httpStatus;

  public MinkCeption(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
