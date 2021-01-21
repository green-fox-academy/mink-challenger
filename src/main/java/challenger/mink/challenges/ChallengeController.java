package challenger.mink.challenges;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ChallengeController {
  private final ChallengeService challengeService;

  @GetMapping("/admin")
  public String renderAdminPage(Model model) {
    model.addAttribute("challenges", challengeService.findAll());
    model.addAttribute("challenge", new Challenge());
    return "admin.html";
  }

  @PostMapping("/addchallenge")
  public String addChallenge(@ModelAttribute ("challenge") Challenge challenge) {
    challengeService.addChallenge(challenge);
    return "redirect:/admin";
  }
}