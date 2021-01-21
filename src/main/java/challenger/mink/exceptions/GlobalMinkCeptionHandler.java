package challenger.mink.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalMinkCeptionHandler {
  private static final Logger logger = LogManager.getLogger(GlobalMinkCeptionHandler.class);


  @ExceptionHandler(MinkCeption.class)
  public ResponseEntity<ErrorResponseDTO> handleTribesException(MinkCeption e) {
    logger.error(
        e.getLocalizedMessage());
    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage());
    return new ResponseEntity<>(errorResponseDTO, e.getHttpStatus());
  }
}
