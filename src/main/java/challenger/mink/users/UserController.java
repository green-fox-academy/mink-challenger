package challenger.mink.users;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {
  private final UserRepository userRepository;
  private final UserService userService;

  @GetMapping(value = "/register")
  public String renderRegisterPage(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping(value = "/register")
  public String registerNewUser(@ModelAttribute User user,
                                RedirectAttributes attributes) throws OccupiedUsernameMinkCeption {

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

  @GetMapping("/verify/{uuid}")
  public String verifyEmail(@PathVariable String uuid) {
    User user =
        userRepository.findUserByUuid(uuid).orElseThrow(() -> new NoSuchElementException("no"));
    if (user.isEmailVerified()) {
      return "redirect:/admin";
    } else {
      user.setEmailVerified(true);
      userService.saveUser(user);
      return "redirect:/nosafe";
    }
  }
}
