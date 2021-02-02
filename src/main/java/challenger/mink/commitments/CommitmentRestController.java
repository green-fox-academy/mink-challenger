package challenger.mink.commitments;

import challenger.mink.challenges.ChallengeService;
import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import challenger.mink.commitments.minkceptions.CommitmentAlreadySetDoneMinkCeption;
import challenger.mink.commitments.minkceptions.IllegalDateMinkCeption;
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

  @GetMapping("/commitmentDAOsByUser") // müxik
  public ResponseEntity<?> commitmentDAOsByUser(Principal principal)
      throws NoSuchUserMinkCeption {
    User user = userService.findUserByName(principal.getName());
    return ResponseEntity.ok(commitmentService.findAllCommitmentDAOByUser(user));
  }

  @PostMapping("/commitment") // müxik
  public ResponseEntity<?> commitmentDTO(@RequestBody CommitmentDTO commitmentDTO, Principal principal)
      throws NoSuchChallengeMinkCeption, InvalidInputCommitmentMinkCeption, NoSuchUserMinkCeption {
    User user = userService.findUserByName(principal.getName());
    commitmentService.addCommitmentFromDTO(commitmentDTO, user);
    return ResponseEntity.status(HttpStatus.CREATED).body("Commitment created successfully!");
  }

  @GetMapping("/commitmentById/{commitmentId}") // müxik
  public ResponseEntity<?> commitmentsById(@PathVariable Long commitmentId, Principal principal)
      throws NoSuchCommitmentMinkCeption, NoSuchUserMinkCeption {
    User user = userService.findUserByName(principal.getName());
    return ResponseEntity.ok(commitmentService.findCommitmentDAObyCommitmentId(commitmentId));
  }

  @PostMapping("/editCommitment/{commitmentId}") // müxik
  public ResponseEntity<?> editCommitment(@RequestBody CommitmentDTO commitmentDTO,
                                          @PathVariable Long commitmentId)
      throws InvalidInputCommitmentMinkCeption, NoSuchChallengeMinkCeption,
      NoSuchCommitmentMinkCeption, NoSuchUserMinkCeption {
    User user = userService.findUserByName("Bela");
    commitmentService.modifyCommitmentFromDTO(commitmentDTO, commitmentId, user);
    return ResponseEntity.status(HttpStatus.OK).body("Commitment changed successfully!");
  }

  @DeleteMapping("/delCommitment/{commitmentId}") // müxik
  public ResponseEntity<?> commitment(@PathVariable Long commitmentId)
      throws NoSuchCommitmentMinkCeption {
    commitmentService.deleteCommitment(commitmentId);
    return ResponseEntity.ok("Commitment deleted successfully!");
  }

  @PostMapping("/setCommitmentDone/{commitmentId}") // müxik
  public ResponseEntity<?> commitmentDone(@PathVariable long commitmentId)
      throws NoSuchCommitmentMinkCeption, IllegalDateMinkCeption,
      CommitmentAlreadySetDoneMinkCeption {
    commitmentService.setCommitmentDone(commitmentId);
    return ResponseEntity.ok("Commitment set Done successfully!");
  }
}