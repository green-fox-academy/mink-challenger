//package challenger.mink.users;
//
//import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
//import challenger.mink.users.minkceptions.OccupiedEmailMinkCeption;
//import challenger.mink.users.minkceptions.OccupiedUsernameMinkCeption;
//import lombok.RequiredArgsConstructor;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//  private final UserRepository userRepository;
//  private final UserService userService;
//  private static final Logger logger = LogManager.getLogger(UserController.class);
//
//  @GetMapping(value = "/register")
//  public String renderRegisterPage(Model model) {
//    logger.info("GET/register has been called!");
//    model.addAttribute("user", new User());
//    return "register";
//  }
//
//  @PostMapping(value = "/register")
//  public String registerNewUser(@ModelAttribute User user)
//      throws OccupiedUsernameMinkCeption, OccupiedEmailMinkCeption {
//    logger.info("POST/register has been called!");
//    userService.registerNewUser(user).getId();
//    return "waitforyouremail";
//  }
//
//  @GetMapping(value = "/login")
//  public String renderLoginPage() {
//    return "login";
//  }
//
//  @GetMapping("/verify/{uuid}")
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