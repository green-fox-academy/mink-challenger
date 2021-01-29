package challenger.mink.commitments;

import challenger.mink.challenges.Challenge;
import challenger.mink.challenges.ChallengeRepository;
import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import challenger.mink.commitments.minkceptions.NoSuchCommitmentMinkCeption;
import challenger.mink.users.User;
import challenger.mink.users.UserRepository;
import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitmentService {
  private final CommitmentRepository commitmentRepository;
  private final UserRepository userRepository;
  private final ChallengeRepository challengeRepository;

  public List<Commitment> findAll() {
    return commitmentRepository.findAll();
  }

  public List<Commitment> findAllByUser(long userId) {
    return commitmentRepository.findByUserId(userId);
  }

  public void addCommitment(long userId, String description, String date, Long challengeId)
      throws NoSuchUserMinkCeption, NoSuchChallengeMinkCeption {
    LocalDate localDate = LocalDate.parse(date);
    User user = userRepository.findById(userId).orElseThrow(NoSuchUserMinkCeption::new);
    Challenge challenge = challengeRepository.findById(challengeId)
        .orElseThrow(NoSuchChallengeMinkCeption::new);

    if (!localDate.isBefore(challenge.getStartDate()) &&
        !localDate.isAfter(challenge.getEndDate()) &&
        LocalDate.now().isBefore(challenge.getStartDate())) {
      commitmentRepository
          .save(new Commitment(description, localDate.plusDays(1), user, challenge));
    }
  }

  public Commitment getCommitmentById(long id) throws NoSuchCommitmentMinkCeption {
    return commitmentRepository.findById(id)
        .orElseThrow(NoSuchCommitmentMinkCeption::new);
  }

  public void saveChangedCommitment(long commitmentId, Commitment currentCommitment)
      throws NoSuchCommitmentMinkCeption {
    Commitment commitment = getCommitmentById(commitmentId);
    if (!currentCommitment.getDate().isBefore(commitment.getChallenge().getStartDate()) &&
        !currentCommitment.getDate().isAfter(commitment.getChallenge().getEndDate()) &&
        LocalDate.now().isBefore(commitment.getChallenge().getStartDate())) {

      commitment.setDescription(currentCommitment.getDescription());
      commitment.setDate(currentCommitment.getDate().plusDays(1));
      commitmentRepository.save(commitment);
    }
  }

  public void setCommitmentDone(long commitmentId) throws NoSuchCommitmentMinkCeption {
    Commitment commitment = getCommitmentById(commitmentId);
    if (!LocalDate.now().isBefore(commitment.getDate()) &&
        !LocalDate.now().isAfter(commitment.getChallenge().getEndDate().plusDays(1))) {
      commitment.setDone(true);
      commitmentRepository.save(commitment);
    }
  }

  public void deleteCommitment(long commitmentId) throws NoSuchCommitmentMinkCeption {
    Commitment commitment =
        getCommitmentById(commitmentId);
    if (LocalDate.now().isBefore(commitment.getChallenge().getStartDate())) {
      commitmentRepository.delete(commitment);
    }
  }

  public long getUserIdByCommitmentId(long commitmentId) throws NoSuchCommitmentMinkCeption {
    return getCommitmentById(commitmentId).getUser().getId();
  }
}