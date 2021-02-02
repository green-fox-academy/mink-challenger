package challenger.mink.users;

import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
import challenger.mink.users.minkceptions.OccupiedEmailMinkCeption;
import challenger.mink.users.minkceptions.OccupiedUsernameMinkCeption;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {
  private final UserService userService;
  private static final Logger logger = LogManager.getLogger(UserRestController.class);

  @PostMapping("/registerNewUser")
  public ResponseEntity<?> newUser(@RequestBody UserDTO userDTO)
      throws OccupiedUsernameMinkCeption, OccupiedEmailMinkCeption {
    userService.registerNewUserFromDTO(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.getUsername() +
        " username is created, check your email, to confirm account and start challenges.");
  }

  @GetMapping("/verifyMail/{uuid}")
  public ResponseEntity emailVerifier(@PathVariable String uuid) throws NoSuchUserMinkCeption {
    User user = userService.findUserByUuid(uuid);
    if (user.isEmailVerified()) {
      return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
          .body("You already verified Your account! Feel free to log in!");
    } else {
      user.setEmailVerified(true);
      userService.saveUser(user);
      return ResponseEntity
          .ok("Congratulation, You just verified Your account! Feel free to log in!");
    }
  }
}