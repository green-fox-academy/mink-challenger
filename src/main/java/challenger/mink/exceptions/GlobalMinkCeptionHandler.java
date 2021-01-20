package challenger.mink.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalMinkCeptionHandler {

  @ExceptionHandler(MinkCeption.class)
  public ResponseEntity<ErrorResponseDTO> handleTribesException(MinkCeption e) {

    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage());
    return new ResponseEntity<>(errorResponseDTO, e.getHttpStatus());
  }
}
