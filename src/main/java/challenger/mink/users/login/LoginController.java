package challenger.mink.users.login;

import challenger.mink.users.UserService;
import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<LoginSuccessDTO> login(@RequestBody(required = false)
                                                   LoginRequestDTO loginRequestDTO,
                                               HttpServletResponse response)
      throws NoSuchUserMinkCeption {
    LoginSuccessDTO loginSuccessDTO =
        new LoginSuccessDTO(userService.authenticateExistingUser(loginRequestDTO));
    response.addHeader("X-mink-challenger", userService.authenticateExistingUser(loginRequestDTO));
    return ResponseEntity.status(HttpStatus.OK).body(loginSuccessDTO);
  }
}
