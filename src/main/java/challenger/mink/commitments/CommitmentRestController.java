package challenger.mink.commitments;

import challenger.mink.challenges.ChallengeService;
import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import challenger.mink.commitments.minkceptions.InvalidInputCommitmentMinkCeption;
import challenger.mink.commitments.minkceptions.NoSuchCommitmentMinkCeption;
import challenger.mink.users.User;
import challenger.mink.users.UserService;
import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
import java.security.Principal;
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
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommitmentRestController {
  private final CommitmentService commitmentService;
  private final ChallengeService challengeService;
  private final UserService userService;
  private static final Logger logger = LogManager.getLogger(CommitmentRestController.class);

  @GetMapping("/commitmentDAOsByUser")
// GET "/commitment" : model.addAttribute("commitments", commitmentService.findAllByUser(userId));
// GET "/commitment/{id}" : model.addAttribute("commitments", commitmentService.findAllByUser(userId));
// GET "/editCommitment/{commitmentId}": model.addAttribute("commitments", commitmentService.findAllByUser(commitment.getUser().getId()));
  public ResponseEntity<?> commitmentDAOsByUser(Principal principal)
      throws NoSuchUserMinkCeption {
    User user = userService.findUserByName(principal
        .getName()); // nyílván nem null, hanem a user jwt-tokenből authorizálás és User kikapása
    return ResponseEntity.ok(commitmentService.findAllCommitmentDAOByUser(user));
  }
//  @GetMapping("/commitment")
//  public String renderCommitmentPage(Model model, Principal principal) {
//    long userId = userService.findUserByName(principal.getName()); // ez a JWT tokenből lesz kivéve vagy ez továbbra is működik?
//    logger.info("GET/commitment has been called!");
//    model.addAttribute("commitments", commitmentService.findAllByUser(userId)); // GET "/commitmentDAOsByUser"
//    model.addAttribute("userId", userId);
//    model.addAttribute("challenges", challengeService.findAll()); // GET "/challengeDAOs" (in: ChallengeRepository)
//    return "addcommitment.html";
//  }
////
////// ez megy a lecsóba, egyébként ua., mint az előbbi
////  @GetMapping("/commitment/{id}")
////  public String renderCommitmentPage(Model model, @PathVariable long id) {
////    logger.info("GET/commitment/{id} has been called!");
////    model.addAttribute("commitments", commitmentService.findAllByUser(id)); // GET "/commitmentDAOsByUser"
////    model.addAttribute("userId", id);
////    model.addAttribute("challenges", challengeService.findAll()); // GET "/challengeDAOs"
////    return "addcommitment.html";
////  }

  @PostMapping("/commitment")
// POST "/addcommitment/{userId}"
  public ResponseEntity<?> commitmentDTO(@RequestBody CommitmentDTO commitmentDTO,
                                         Principal principal)
      throws NoSuchChallengeMinkCeption, InvalidInputCommitmentMinkCeption, NoSuchUserMinkCeption {
    User user = userService.findUserByName(principal.getName());
    commitmentService.addCommitmentFromDTO(commitmentDTO, user);
    return ResponseEntity.status(HttpStatus.CREATED).body("Commitment created successfully!");
  }

//
////  @PostMapping("/addcommitment/{userId}")
////// POST "/commitment"
////  public String addCommitment(@PathVariable long userId, String description, String date,
////                              Long challengeId)
////      throws NoSuchUserMinkCeption, NoSuchChallengeMinkCeption {
////    logger.info("POST/addcommitment has been called!");
////    commitmentService.addCommitment(userId, description, date, challengeId);
////    return "redirect:/commitment/" + userId;
////  }

  @GetMapping("/commitmentById/{id}")
// GET "/editCommitment/{commitmentId}":
  public ResponseEntity<?> commitmentsById(@PathVariable Long id, Principal principal)
      throws NoSuchCommitmentMinkCeption {
    return ResponseEntity.ok(commitmentService.getCommitmentById(id));
  }

////  @GetMapping("/editCommitment/{commitmentId}")
////  public String renderEditCommitmentPage(Model model, @PathVariable long commitmentId)
////      throws NoSuchCommitmentMinkCeption {
////    logger.info("GET/editCommitment has been called!");
////    Commitment commitment = commitmentService.getCommitmentById(commitmentId);
////    model
////        .addAttribute("commitments", commitmentService.findAllByUser(commitment.getUser().getId())); // @GetMapping("/commitmentDAOsByUser")
////    model.addAttribute("currentCommitment", commitment); // GET /commitmentById/{id}
////    return "editcommitment.html";
////  }
////

  @PostMapping("/editCommitment/{commitmentId}")
// POST /saveCommitment/{commitmentId}
  public ResponseEntity<?> editCommitment(@RequestBody CommitmentDTO commitmentDTO,
                                          Principal principal, @PathVariable Long commitmentId)
      throws InvalidInputCommitmentMinkCeption, NoSuchChallengeMinkCeption,
      NoSuchCommitmentMinkCeption, NoSuchUserMinkCeption {
    User user = userService.findUserByName(principal.getName());
    commitmentService.modifyCommitmentFromDTO(commitmentDTO, commitmentId, user);
    return ResponseEntity.status(HttpStatus.OK).body("Commitment changed successfully!");
  }

////  @PostMapping("/saveCommitment/{commitmentId}")
////  public String editCommitment(@ModelAttribute("currentCommitment") Commitment currentCommitment,
////                               @PathVariable long commitmentId) throws NoSuchCommitmentMinkCeption {
////    logger.info("POST/saveCommitment has been called!");
////    commitmentService.saveChangedCommitment(commitmentId, currentCommitment);
////    return "redirect:/commitment/" +
////        commitmentService.getCommitmentById(commitmentId).getUser().getId();
////  }

  //  @SneakyThrows // whatever...
  @DeleteMapping("/delCommitment/{commitmentId}")
// POST /delCommitment/{commitmentId}
  public ResponseEntity<?> commitment(Principal principal, @PathVariable Long commitmentId)
      throws NoSuchCommitmentMinkCeption {
    commitmentService.deleteCommitment(commitmentId);
    return ResponseEntity.ok("Commitment deleted successfully!");
  }

////  @SneakyThrows
////  @PostMapping("/delCommitment/{commitmentId}")
////  public String delCommitment(@PathVariable long commitmentId) throws NoSuchCommitmentMinkCeption {
////    long userId = commitmentService.getUserIdByCommitmentId(commitmentId);
////    commitmentService.deleteCommitment(commitmentId);
////    return "redirect:/commitment/" + userId;
////  }

  @PostMapping("/setCommitmentDone/{commitmentId}")
// POST /setCommitmentDone/{commitmentId}
  public ResponseEntity<?> commitmentDone(Principal principal, @PathVariable long commitmentId)
      throws NoSuchCommitmentMinkCeption {
    commitmentService.setCommitmentDone(commitmentId);
    return ResponseEntity.ok("Commitment set Done successfully!");
  }
}

////  @PostMapping("/setCommitmentDone/{commitmentId}")
////  public String setCommitmentDone(@PathVariable long commitmentId)
////      throws NoSuchCommitmentMinkCeption {
////    long userId = commitmentService.getUserIdByCommitmentId(commitmentId);
////    commitmentService.setCommitmentDone(commitmentId);
////    return "redirect:/commitment/" + userId;
////  }
////}