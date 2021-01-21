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
    User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    Challenge challenge = challengeRepository.findById(challengeId)
        .orElseThrow(NoSuchElementException::new);
    LocalDate localDate = LocalDate.parse(date);
    commitmentRepository.save(new Commitment(description, localDate, user, challenge));
  }

  public Commitment getCommitmentById(long id) {
    return commitmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  public void saveChangedCommitment(long commitmentId, Commitment currentCommitment) {
    Commitment commitment =
        commitmentRepository.findById(commitmentId).orElseThrow(NoSuchElementException::new);
    commitment.setDescription(currentCommitment.getDescription());
    commitment.setDate(currentCommitment.getDate().plusDays(1));
    commitment.setDone(currentCommitment.isDone());
    commitmentRepository.save(commitment);
  }

  public void deleteCommitment(long commitmentId) {
    Commitment commitment = commitmentRepository.findById(commitmentId).orElseThrow(NoSuchElementException::new);
    commitmentRepository.delete(commitment);
  }

  public long getUserIdByCommitmentId(long commitmentId) {
    Commitment commitment = commitmentRepository.findById(commitmentId).orElseThrow(NoSuchElementException::new);
    return commitment.getUser().getId();
  }
}
