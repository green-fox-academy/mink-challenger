package challenger.mink.users;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private static final Logger logger = LogManager.getLogger(UserController.class);

  @GetMapping(value = "/register")
  public String renderRegisterPage(Model model) {
    logger.info("GET/register has been called!");
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping(value = "/register")
  public String registerNewUser(@ModelAttribute User user) throws OccupiedUsernameMinkCeption {
    logger.info("POST/register has been called!");
    return "redirect:/commitment/" + userService.registerNewUser(user).getId();
  }

  @GetMapping(value = "/nosafe")
  public String renderNoSafe() {
    return "nosafe";
  }

  @GetMapping(value = "/safe")
  public String renderSafe() {
    return "safe";
  }
}
