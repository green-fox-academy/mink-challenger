//package challenger.mink.commitments;
//
//import challenger.mink.challenges.ChallengeService;
//import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
//import challenger.mink.commitments.minkceptions.NoSuchCommitmentMinkCeption;
//import challenger.mink.users.User;
//import challenger.mink.users.UserService;
//import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
//import java.security.Principal;
//import javax.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class CommitmentRestController {
//  private final CommitmentService commitmentService;
//  private final ChallengeService challengeService;
//  private final UserService userService;
//  private static final Logger logger = LogManager.getLogger(CommitmentRestController.class);
//
//
//  @GetMapping ("/commitmentDAOsByUser")
//// GET "/commitment" : model.addAttribute("commitments", commitmentService.findAllByUser(userId));
//// GET "/commitment/{id}" : model.addAttribute("commitments", commitmentService.findAllByUser(userId));
//
//  public ResponseEntity<?> commitmentDAOsByUser(HttpServletRequest request){
//    User user = null; // nyílván nem null, hanem a user jwt-tokenből authorizálás és User kikapása
//    return ResponseEntity.ok(commitmentService.findAllCommitmentDAOByUser(user));
//  }
//
////  @GetMapping("/commitment")
////  public String renderCommitmentPage(Model model, Principal principal) {
////    long userId = userService.findUserByName(principal.getName()); // ez a JWT tokenből lesz kivéve
////    logger.info("GET/commitment has been called!");
////    model.addAttribute("commitments", commitmentService.findAllByUser(userId)); // GET "/commitmentDAOsByUser"
////    model.addAttribute("userId", userId);
////    model.addAttribute("challenges", challengeService.findAll()); // GET "/challengeDAOs"
////    return "addcommitment.html";
////  }
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
//
//
////  @PostMapping ("/commitment")
//// POST "/addcommitment/{userId}"
////  public ResponseEntity<?> commitmentDTO(@RequestBody CommitmentDTO commitmentDTO){
////
////  }
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
////
////  @GetMapping("/editCommitment/{commitmentId}")
////  public String renderEditCommitmentPage(Model model, @PathVariable long commitmentId)
////      throws NoSuchCommitmentMinkCeption {
////    logger.info("GET/editCommitment has been called!");
////    Commitment commitment = commitmentService.getCommitmentById(commitmentId);
////    model
////        .addAttribute("commitments", commitmentService.findAllByUser(commitment.getUser().getId()));
////    model.addAttribute("currentCommitment", commitment);
////    return "editcommitment.html";
////  }
////
////  @PostMapping("/saveCommitment/{commitmentId}")
////  public String editCommitment(@ModelAttribute("currentCommitment") Commitment currentCommitment,
////                               @PathVariable long commitmentId) throws NoSuchCommitmentMinkCeption {
////    logger.info("POST/saveCommitment has been called!");
////    commitmentService.saveChangedCommitment(commitmentId, currentCommitment);
////    return "redirect:/commitment/" +
////        commitmentService.getCommitmentById(commitmentId).getUser().getId();
////  }
////
////  @SneakyThrows
////  @PostMapping("/delCommitment/{commitmentId}")
////  public String delCommitment(@PathVariable long commitmentId) throws NoSuchCommitmentMinkCeption {
////    long userId = commitmentService.getUserIdByCommitmentId(commitmentId);
////    commitmentService.deleteCommitment(commitmentId);
////    return "redirect:/commitment/" + userId;
////  }
////
////  @PostMapping("/setCommitmentDone/{commitmentId}")
////  public String setCommitmentDone(@PathVariable long commitmentId)
////      throws NoSuchCommitmentMinkCeption {
////    long userId = commitmentService.getUserIdByCommitmentId(commitmentId);
////    commitmentService.setCommitmentDone(commitmentId);
////    return "redirect:/commitment/" + userId;
////  }
////
////}