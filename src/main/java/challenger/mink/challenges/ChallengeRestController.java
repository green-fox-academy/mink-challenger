package challenger.mink.challenges;

import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChallengeRestController {
  private final ChallengeService challengeService;
  private static final Logger logger = LogManager.getLogger(ChallengeRestController.class);

  @GetMapping("/challengeDAOs")
  public ResponseEntity<?> allChallengeDAOs() { // ide kellett a JWT-hez HttpRequest de most nem tudom mi kellene?
    logger.info("GET/api/challengeDAOs has been called");
    return ResponseEntity.ok(challengeService.findAllChallengeDAO());
  }

  @PostMapping("/addnewChallengeDTO")
  public ResponseEntity<?> newChallenge(@RequestBody ChallengeDTO challengeDTO) {
    logger.info("POST/api/addnewChallengeDTO has been called");
    challengeService.addChallengeFromDTO(challengeDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body("You have successfully added a new challenge!");
  }

  @GetMapping("currentChallengeDAO/{challengeId}")
  // GET "/admin/{id}": model.addAttribute("currentChallenge", challengeService.getChallengeById(id));
  public ResponseEntity<?> currentChallenge(@PathVariable long challengeId)
      throws NoSuchChallengeMinkCeption {
    return ResponseEntity.ok(challengeService.getChallengeDAObyId(challengeId));
  }

  @PostMapping("/addchallengeDTO/{challengeId}")
  // POST "/admin/{id}" : challengeService.addChallenge(challenge);
  public ResponseEntity<?> addChallenge(@RequestBody ChallengeDTO challengeDTO,
                                        @PathVariable long challengeId) {
    return ResponseEntity.status(HttpStatus.CREATED).body("át van csinálva a challenge-ed");
  }

  @DeleteMapping("/delChallengeById/{challengeId}")
  // POST "/delChallenge/{challengeId}"
  public ResponseEntity<?> delChallenge(@PathVariable long challengeId)
      throws NoSuchChallengeMinkCeption {
    challengeService.deleteChallenge(challengeId);
    return ResponseEntity.ok("törölted a " + challengeId + ". számú challenge-et");
  }
}

//  @GetMapping("/admin"))
//  public String renderAdminPage(Model model) {
//    logger.info("GET/admin has been called");
//    model.addAttribute("challenges", challengeService.findAll()); // GET "/challengeDAOs"
//    model.addAttribute("challenge", new Challenge()); // POST "/newChallengeDTO"
//    return "admin.html";
//  }
//
//  @GetMapping("/admin/{id}")
//  public String renderAdminWithSpecificChallenge(Model model, @PathVariable long id)
//      throws NoSuchChallengeMinkCeption {
//    logger.info("GET/admin/{id} has been called");
//    model.addAttribute("challenges", challengeService.findAll()); // GET "/challengeDAOs"
//    model.addAttribute("currentChallenge", challengeService.getChallengeById(id)); // GET "currentChallengeDAO/{id}"
//    return "admin_change_challenge.html";
//  }
//
//  @PostMapping("/addchallenge") // POST "/addchallengeDTO"
//  public String addChallenge(@ModelAttribute("currentChallenge") Challenge challenge) {
//    logger.info("POST/addchallenge has been called");
//    challengeService.addChallenge(challenge);
//    return "redirect:/admin";
//  }
//
//  @PostMapping("/admin/{id}") // POST "/addchallengeDTO/{challengeId}"
//  public String addChallenge(@ModelAttribute("challenge") Challenge challenge,
//                             @PathVariable long id) throws NoSuchChallengeMinkCeption {
//    logger.info("POST/admin/{id} has been called");
//    Challenge currentChallenge = challengeService.getChallengeById(id);
//    currentChallenge.setName(challenge.getName());
//    currentChallenge.setStartDate(challenge.getStartDate().plusDays(1));
//    currentChallenge.setEndDate(challenge.getEndDate().plusDays(1));
//    currentChallenge.setMinimumCommitment(challenge.getMinimumCommitment());
//    challengeService.addChallenge(challenge);
//    return "redirect:/admin";
//  }
//
//  @PostMapping("/delChallenge/{challengeId}") // DELETE "/delChallengeById/{challengeId}"
//  public String delChallegne(@PathVariable long challengeId) {
//    challengeService.deleteChallenge(challengeId);
//    return "redirect:/admin";
//  }
//}
