package challenger.mink.challenges;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {
  private final ChallengeRepository challengeRepository;

  public List<Challenge> findAll() {
    return challengeRepository.findAll();
  }

  public void addChallenge(Challenge challenge) {
    if (challenge.getStartDate().isAfter(LocalDate.now()) && challenge.getStartDate().isBefore(challenge.getEndDate())) {
      challenge.setStartDate(challenge.getStartDate().plusDays(1));
      challenge.setEndDate(challenge.getEndDate().plusDays(1));
      challengeRepository.save(challenge);
    }
  }

  public Challenge getChallengeById(long id) throws NoSuchChallengeMinkCeption {
    return challengeRepository.findById(id).orElseThrow(NoSuchChallengeMinkCeption::new);
  }

  public void deleteChallenge(long challengeId) {
    Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(
        NoSuchElementException::new);
    if(LocalDate.now().isBefore(challenge.getStartDate()))
    challengeRepository.delete(challenge);
  }
}
