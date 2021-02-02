package challenger.mink.challenges;

import challenger.mink.challenges.minkceptions.InvalidChallengeException;
import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import challenger.mink.commitments.minkceptions.ChallengeNameDuplicationMinkCeption;
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
  public ResponseEntity<?> newChallenge(@RequestBody ChallengeDTO challengeDTO)
      throws NoSuchChallengeMinkCeption, ChallengeNameDuplicationMinkCeption,
      InvalidChallengeException {
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
                                        @PathVariable long challengeId)
      throws NoSuchChallengeMinkCeption, ChallengeNameDuplicationMinkCeption,
      InvalidChallengeException {
    Challenge currentChallenge = challengeService.getChallengeById(challengeId);
    challengeService.changeChallenge(currentChallenge, challengeDTO);
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