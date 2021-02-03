package challenger.mink.users;

import challenger.mink.users.login.LoginRequestDTO;
import challenger.mink.users.login.LoginSuccessDTO;
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
//  GET "/register"
//  POST "/register"
  public ResponseEntity<?> newUser(@RequestBody UserDTO userDTO)
      throws OccupiedUsernameMinkCeption, OccupiedEmailMinkCeption {
    userService.registerNewUserFromDTO(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.getUsername() +
        " username is created, check your email, to confirm account and start challenges.");
  }

//  @GetMapping(value = "/register")
//// POST "/registerNewUser"
//  public String renderRegisterPage(Model model) {
//    logger.info("GET/register has been called!");
//    model.addAttribute("user", new User());
//    return "register";
//  }
//
//  @PostMapping(value = "/register")
//// POST "/registerNewUser"
//  public String registerNewUser(@ModelAttribute User user)
//      throws OccupiedUsernameMinkCeption, OccupiedEmailMinkCeption {
//    logger.info("POST/register has been called!");
//    userService.registerNewUser(user).getId();
//    return "waitforyouremail";
//  }
//
//  @GetMapping(value = "/login")
////  0 no endpoint?
//  public String renderLoginPage() {
//    return "login";
//  }

  @GetMapping("/verifyMail/{uuid}")
//  GET "/verify/{uuid}"
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

//  @GetMapping("/verify/{uuid}")
////  GET "/verifyMail/{uuid}"
//  public String verifyEmail(@PathVariable String uuid) throws NoSuchUserMinkCeption {
//    User user =
//        userRepository.findUserByUuid(uuid).orElseThrow(NoSuchUserMinkCeption::new);
//    if (user.isEmailVerified()) {
//      return "redirect:/admin";
//    } else {
//      user.setEmailVerified(true);
//      userService.saveUser(user);
//      return "login";
//    }
//  }
//}


  @PostMapping("/login")
  public ResponseEntity<LoginSuccessDTO> login(@RequestBody(required = false)
                                                   LoginRequestDTO loginRequestDTO) {
//    logger.traceEntry("POST/login has been called!");
//    logger.info("POST/login Successful login with username: {}!", loginRequestDTO.getUsername());
//    logger.traceExit("POST/login with username: {}", loginRequestDTO.getUsername());
    LoginSuccessDTO loginSuccessDTO =
        new LoginSuccessDTO(userService.authenticateExistingUser(loginRequestDTO));
//    userService.updateStatus(loginRequestDTO.getUsername(), loginSuccessDTO);
    return ResponseEntity.status(HttpStatus.OK)
        .body(loginSuccessDTO);
  }
}