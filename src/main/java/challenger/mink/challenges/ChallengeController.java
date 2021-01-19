package challenger.mink.challenges;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/admin/{id}")
  public String renderAdminWithSpecificChallenge(Model model, @PathVariable long id) {
    model.addAttribute("challenges", challengeService.findAll());
    model.addAttribute("currentChallenge", challengeService.getChallengeById(id));
    return "admin_change_challenge.html";
  }

  @PostMapping("/addchallenge")
  public String addChallenge(@ModelAttribute("currentChallenge") Challenge challenge) {
    challengeService.addChallenge(challenge);
    return "redirect:/admin";
  }

  @PostMapping("/admin/{id}")
  public String addChallenge(@ModelAttribute("challenge") Challenge challenge,
                             @PathVariable long id) {
    Challenge currentChallenge = challengeService.getChallengeById(id);
    currentChallenge.setName(challenge.getName());
    currentChallenge.setStartDate(challenge.getStartDate());
    currentChallenge.setEndDate(challenge.getEndDate());
    currentChallenge.setMinimumCommitment(challenge.getMinimumCommitment());
    challengeService.addChallenge(challenge);
    return "redirect:/admin";
  }
}


