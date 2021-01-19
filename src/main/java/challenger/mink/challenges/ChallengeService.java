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
    challengeRepository.save(challenge);
  }
}
