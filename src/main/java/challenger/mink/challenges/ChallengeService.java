package challenger.mink.challenges;

import java.util.List;
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
    challenge.setStartDate(challenge.getStartDate().plusDays(1));
    challenge.setEndDate(challenge.getEndDate().plusDays(1));
    challengeRepository.save(challenge);
  }

  public Challenge getChallengeById(long id) throws NoSuchChallengeMinkCeption {
    return challengeRepository.findById(id).orElseThrow(NoSuchChallengeMinkCeption::new);
  }
}
