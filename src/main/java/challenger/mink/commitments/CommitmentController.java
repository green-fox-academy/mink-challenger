package challenger.mink.commitments;

import challenger.mink.challenges.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommitmentController {
  private final CommitmentService commitmentService;
  private final ChallengeService challengeService;

  @GetMapping("/commitment/{userId}")
  public String renderCommitmentPage(Model model, @PathVariable long userId) {
    model.addAttribute("commitments", commitmentService.findAllByUser(userId));
    model.addAttribute("userId", userId);
    model.addAttribute("challenges", challengeService.findAll());
    return "addcommitment.html";
  }

  @PostMapping("/addcommitment/{userId}")
  public String addCommitment(@PathVariable long userId, String description, String date,
                              Long challengeId) {
    commitmentService.addCommitment(userId, description, date, challengeId);
    return "redirect:/commitment/" + userId;
  }

  @GetMapping("/editCommitment/{commitmentId}")
  public String renderEditCommitmentPage(Model model, @PathVariable long commitmentId) {
    Commitment commitment = commitmentService.getCommitmentById(commitmentId);
    model
        .addAttribute("commitments", commitmentService.findAllByUser(commitment.getUser().getId()));
    model.addAttribute("currentCommitment", commitment);
    return "editcommitment.html";
  }

  @PostMapping("/saveCommitment/{commitmentId}")
  public String editCommitment(@ModelAttribute("currentCommitment") Commitment currentCommitment,
                               @PathVariable long commitmentId) {
    commitmentService.saveChangedCommitment(commitmentId, currentCommitment);
    return "redirect:/commitment/" +
        commitmentService.getCommitmentById(commitmentId).getUser().getId();
  }
}