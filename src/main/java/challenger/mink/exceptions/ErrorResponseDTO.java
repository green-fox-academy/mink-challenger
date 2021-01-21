package challenger.mink.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDTO {
  private String status;
  private String message;

  public ErrorResponseDTO(String message) {
    this.status = "error";
    this.message = message;
  }
}
