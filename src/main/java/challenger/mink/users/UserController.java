package challenger.mink.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {
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
}
