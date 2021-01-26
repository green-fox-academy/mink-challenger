package challenger.mink.commitments;

import challenger.mink.challenges.Challenge;
import challenger.mink.challenges.ChallengeRepository;
import challenger.mink.users.User;
import challenger.mink.users.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
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

  public void addCommitment(long userId, String description, String date, Long challengeId) {
    LocalDate localDate = LocalDate.parse(date);
    User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    Challenge challenge = challengeRepository.findById(challengeId)
        .orElseThrow(NoSuchElementException::new);

    if (!localDate.isBefore(challenge.getStartDate()) &&
        !localDate.isAfter(challenge.getEndDate()) &&
        LocalDate.now().isBefore(challenge.getStartDate())) {
      commitmentRepository
          .save(new Commitment(description, localDate.plusDays(1), user, challenge));
    }
  }

  public Commitment getCommitmentById(long id) {
    return commitmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  public void saveChangedCommitment(long commitmentId, Commitment currentCommitment) {
    Commitment commitment =
        commitmentRepository.findById(commitmentId).orElseThrow(NoSuchElementException::new);
    if (!currentCommitment.getDate().isBefore(commitment.getChallenge().getStartDate()) &&
        !currentCommitment.getDate().isAfter(commitment.getChallenge().getEndDate()) &&
        LocalDate.now().isBefore(commitment.getChallenge().getStartDate())) {

      commitment.setDescription(currentCommitment.getDescription());
      commitment.setDate(currentCommitment.getDate().plusDays(1));
      commitmentRepository.save(commitment);
    }
  }

  public void setCommitmentDone(long commitmentId) {
    Commitment commitment =
        commitmentRepository.findById(commitmentId).orElseThrow(NoSuchElementException::new);
    if (!LocalDate.now().isBefore(commitment.getDate()) &&
        !LocalDate.now().isAfter(commitment.getChallenge().getEndDate().plusDays(1))) {
      commitment.setDone(true);
      commitmentRepository.save(commitment);
    }
  }

  public void deleteCommitment(long commitmentId) {
    Commitment commitment =
        commitmentRepository.findById(commitmentId).orElseThrow(NoSuchElementException::new);
    if (LocalDate.now().isBefore(commitment.getChallenge().getStartDate())) {
      commitmentRepository.delete(commitment);
    }
  }

  public long getUserIdByCommitmentId(long commitmentId) {
    Commitment commitment =
        commitmentRepository.findById(commitmentId).orElseThrow(NoSuchElementException::new);
    return commitment.getUser().getId();
  }
}