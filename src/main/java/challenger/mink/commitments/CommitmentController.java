package challenger.mink.commitments;

import challenger.mink.challenges.Challenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommitmentController {
  private final CommitmentService commitmentService;

  @GetMapping("/commitment")
  public String renderCommitmentPage (Model model){
    model.addAttribute("challenges",commitmentService.findAll());
    return "commitment";
  }

  @PostMapping("/addcommitment")
  public String addCommitment(Model model, @ModelAttribute("commitment") Commitment commitment){
    commitmentService.addCommitment(commitment);
    return "redirect:/admin";
  }
}
