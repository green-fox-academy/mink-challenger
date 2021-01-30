package challenger.mink.challenges;

import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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
    if (challenge.getStartDate().isAfter(LocalDate.now()) &&
        challenge.getStartDate().isBefore(challenge.getEndDate())) {
      challenge.setStartDate(challenge.getStartDate());
      challenge.setStartDatePlusOneDay(); // TODO
      challenge.setEndDate(challenge.getEndDate().plusDays(1));
      challengeRepository.save(challenge);
    }
  }

  public Challenge getChallengeById(long id) throws NoSuchChallengeMinkCeption {
    return challengeRepository.findById(id).orElseThrow(NoSuchChallengeMinkCeption::new);
  }

  public void deleteChallenge(long challengeId) throws NoSuchChallengeMinkCeption {
    Challenge challenge = getChallengeById(challengeId);
    if (LocalDate.now().isBefore(challenge.getStartDate())) {
      challengeRepository.delete(challenge);
    }
  }

  public List<ChallengeDAO> findAllChallengeDAO() {
//    List<ChallengeDAO> challengeDAOs = new ArrayList<>();
//    for (Object[] tuple : challengeRepository.findAllChallengeDAO()) {
//      challengeDAOs.add(new ChallengeDAO(tuple));
//    }
    return challengeRepository.findAllChallengeDAO().stream().map(ChallengeDAO::new)
        .collect(Collectors.toList());
  }

  public void addChallengeFromDTO(ChallengeDTO challengeDTO) {
    challengeRepository.save(new Challenge(challengeDTO.getName(), challengeDTO.getStartDate(),
        challengeDTO.getEndDate(), challengeDTO.getMinimumCommitment()));
  }

  public ChallengeDAO getChallengeDAObyId(long challengeId) throws NoSuchChallengeMinkCeption {
    Object[] tuple =
        challengeRepository.findDAOById(challengeId).orElseThrow(NoSuchChallengeMinkCeption::new);
    return new ChallengeDAO(tuple);
  }
}