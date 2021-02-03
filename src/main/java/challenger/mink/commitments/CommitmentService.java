package challenger.mink.commitments;

import challenger.mink.challenges.Challenge;
import challenger.mink.challenges.ChallengeRepository;
import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import challenger.mink.commitments.minkceptions.CommitmentAlreadySetDoneMinkCeption;
import challenger.mink.commitments.minkceptions.IllegalDateMinkCeption;
import challenger.mink.commitments.minkceptions.InvalidInputCommitmentMinkCeption;
import challenger.mink.commitments.minkceptions.InvalidUserMinkCeption;
import challenger.mink.commitments.minkceptions.NoSuchCommitmentMinkCeption;
import challenger.mink.users.User;
import challenger.mink.users.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitmentService {
  private final CommitmentRepository commitmentRepository;
  private final UserRepository userRepository;
  private final ChallengeRepository challengeRepository;

  public Commitment getCommitmentById(long id) throws NoSuchCommitmentMinkCeption {
    return commitmentRepository.findById(id)
        .orElseThrow(NoSuchCommitmentMinkCeption::new);
  }

  public void setCommitmentDone(long commitmentId, User user)
      throws NoSuchCommitmentMinkCeption, IllegalDateMinkCeption,
      CommitmentAlreadySetDoneMinkCeption, InvalidUserMinkCeption {
    Commitment commitment = getCommitmentById(commitmentId);
    if (/*LocalDate.now().isBefore(commitment.getDate())  || */
        LocalDate.now().isAfter(commitment.getChallenge().getEndDate().plusDays(1))) {
      throw new IllegalDateMinkCeption();
    } else if (user != commitment.getUser()) {
      throw new InvalidUserMinkCeption();
    } else if (commitment.isDone()) {
      throw new CommitmentAlreadySetDoneMinkCeption();
    } else {
      commitment.setDone(true);
      commitment.setCommitmentDatePlusOneDay();
      commitmentRepository.save(commitment);
    }
  }

  public void deleteCommitment(long commitmentId, User user)
      throws NoSuchCommitmentMinkCeption, InvalidInputCommitmentMinkCeption,
      InvalidUserMinkCeption {
    Commitment commitment = getCommitmentById(commitmentId);
    if (!LocalDate.now().isBefore(commitment.getChallenge().getStartDate())) {
      throw new InvalidInputCommitmentMinkCeption();
    } else if (commitmentRepository.getUserIdByCommitmentId(commitmentId) != user.getId()) {
      throw new InvalidUserMinkCeption();
    } else {
      commitmentRepository.delete(commitment);
    }
  }

  public List<CommitmentDAO> findAllCommitmentDAOByUser(User user) {
    return commitmentRepository.findDAOsByUserId(user.getId()).stream().map(CommitmentDAO::new)
        .collect(Collectors.toList());
  }

  public void addCommitmentFromDTO(CommitmentDTO commitmentDTO, User user)
      throws NoSuchChallengeMinkCeption, InvalidInputCommitmentMinkCeption {
    Challenge challenge = challengeRepository.findById(commitmentDTO.getChallengeId())
        .orElseThrow(NoSuchChallengeMinkCeption::new);
    if (unvalidateCommitment(commitmentDTO, challenge)) {
      throw new InvalidInputCommitmentMinkCeption();
    } else {
      commitmentRepository.save(
          new Commitment(commitmentDTO.getDescription(), commitmentDTO.getDate().plusDays(1), user,
              challenge));
    }
  }

  public void modifyCommitmentFromDTO(CommitmentDTO commitmentDTO, Long commitmentId, User user)
      throws InvalidInputCommitmentMinkCeption, NoSuchChallengeMinkCeption,
      NoSuchCommitmentMinkCeption, InvalidUserMinkCeption {
    Commitment commitment =
        commitmentRepository.findById(commitmentId).orElseThrow(NoSuchCommitmentMinkCeption::new);
    Challenge challenge = challengeRepository.findById(commitmentDTO.getChallengeId())
        .orElseThrow(NoSuchChallengeMinkCeption::new);
    if (unvalidateCommitment(commitmentDTO, challenge)) {
      throw new InvalidInputCommitmentMinkCeption();
    } else if (user != commitment.getUser()) {
      throw new InvalidUserMinkCeption();
    } else {
      commitment.setDescription(commitmentDTO.getDescription());
      commitment.setDate(commitmentDTO.getDate().plusDays(1));
      commitment.setChallenge(challengeRepository.findById(commitmentDTO.getChallengeId())
          .orElseThrow(NoSuchChallengeMinkCeption::new));
      commitmentRepository.save(commitment);
    }
  }

  private boolean unvalidateCommitment(CommitmentDTO commitmentDTO, Challenge challenge) {
    return commitmentDTO.getDescription().equals("") || commitmentDTO.getDescription() == null ||
        commitmentDTO.getChallengeId() == null ||
        commitmentDTO.getDate().isAfter(challenge.getEndDate()) ||
        commitmentDTO.getDate().isBefore(challenge.getStartDate()) || LocalDate.now()
        .isAfter(challenge.getStartDate());
  }

  public CommitmentDAO findCommitmentDAObyCommitmentId(long commitmentId, User user)
      throws NoSuchCommitmentMinkCeption, InvalidUserMinkCeption {
    if (!commitmentRepository.existsById(commitmentId)) {
      throw new NoSuchCommitmentMinkCeption();
    } else if (commitmentRepository.getUserIdByCommitmentId(commitmentId) != user.getId()) {
      throw new InvalidUserMinkCeption();
    } else {
      return new CommitmentDAO(commitmentRepository.findDAOByCommitmentId(commitmentId).get(0));
    }
  }
}