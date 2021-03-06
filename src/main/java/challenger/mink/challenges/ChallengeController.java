package challenger.mink.challenges;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
  private static final Logger logger = LogManager.getLogger(ChallengeController.class);


  @GetMapping("/")
  public String renderMainPage() {
    return "main.html";
  }

  @GetMapping("/admin")
  public String renderAdminPage(Model model) {
    logger.info("GET/admin has been called");
    model.addAttribute("challenges", challengeService.findAll());
    model.addAttribute("challenge", new Challenge());
    return "admin.html";
  }

  @GetMapping("/admin/{id}")
  public String renderAdminWithSpecificChallenge(Model model, @PathVariable long id)
      throws NoSuchChallengeMinkCeption {
    logger.info("GET/admin/{id} has been called");
    model.addAttribute("challenges", challengeService.findAll());
    model.addAttribute("currentChallenge", challengeService.getChallengeById(id));
    return "admin_change_challenge.html";
  }

  @PostMapping("/addchallenge")
  public String addChallenge(@ModelAttribute("currentChallenge") Challenge challenge) {
    logger.info("POST/addchallenge has been called");
    challengeService.addChallenge(challenge);
    return "redirect:/admin";
  }

  @PostMapping("/admin/{id}")
  public String addChallenge(@ModelAttribute("challenge") Challenge challenge,
                             @PathVariable long id) throws NoSuchChallengeMinkCeption {
    logger.info("POST/admin/{id} has been called");
    Challenge currentChallenge = challengeService.getChallengeById(id);
    currentChallenge.setName(challenge.getName());
    currentChallenge.setStartDate(challenge.getStartDate().plusDays(1));
    currentChallenge.setEndDate(challenge.getEndDate().plusDays(1));
    currentChallenge.setMinimumCommitment(challenge.getMinimumCommitment());
    challengeService.addChallenge(challenge);
    return "redirect:/admin";
  }
}