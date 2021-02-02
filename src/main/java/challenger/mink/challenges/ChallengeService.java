package challenger.mink.challenges;

import challenger.mink.challenges.minkceptions.InvalidChallengeException;
import challenger.mink.challenges.minkceptions.NoSuchChallengeMinkCeption;
import challenger.mink.commitments.minkceptions.ChallengeNameDuplicationMinkCeption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {
  private final ChallengeRepository challengeRepository;

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
    return challengeRepository.findAllChallengeDAO().stream().map(ChallengeDAO::new)
        .collect(Collectors.toList());
  }

  public void addChallengeFromDTO(ChallengeDTO challengeDTO)
      throws ChallengeNameDuplicationMinkCeption,
      InvalidChallengeException {
    if (challengeRepository.findAllChallengeNames().contains(challengeDTO.getName())) {
      throw new ChallengeNameDuplicationMinkCeption();  // to avoid DataIntegrityViolationException
    } else if (!challengeDTO.getStartDate().isAfter(LocalDate.now()) ||
        !challengeDTO.getStartDate().isBefore(challengeDTO.getEndDate())) {
      throw new InvalidChallengeException();
    } else {
      challengeRepository
          .save(new Challenge(challengeDTO.getName(), challengeDTO.getStartDate().plusDays(1),
              challengeDTO.getEndDate().plusDays(1), challengeDTO.getMinimumCommitment()));
    }
  }

  public ChallengeDAO getChallengeDAObyId(long challengeId) throws NoSuchChallengeMinkCeption {
    Object[] tuple =
        challengeRepository.findDAOById(challengeId).orElseThrow(NoSuchChallengeMinkCeption::new);
    return new ChallengeDAO(tuple);
  }

  public void changeChallenge(Challenge currentChallenge, ChallengeDTO challengeDTO)
      throws ChallengeNameDuplicationMinkCeption,
      InvalidChallengeException {
    if (!currentChallenge.getName().equals(challengeDTO.getName()) &&
        challengeRepository.findAllChallengeNames().contains(challengeDTO.getName())) {
      throw new ChallengeNameDuplicationMinkCeption();  // to avoid sql DataIntegrityViolationException
    } else if (!LocalDate.now().isBefore(currentChallenge.getStartDate()) ||
        !challengeDTO.getStartDate().isAfter(LocalDate.now()) ||
        !challengeDTO.getStartDate().isBefore(challengeDTO.getEndDate())) {
      throw new InvalidChallengeException();
    } else {
      currentChallenge.setStartDate(challengeDTO.getStartDate());
      currentChallenge.setStartDatePlusOneDay();
      currentChallenge.setEndDate(challengeDTO.getEndDate());
      currentChallenge.setEndDatePlusOneDay();
      currentChallenge.setMinimumCommitment(challengeDTO.getMinimumCommitment());
      currentChallenge.setName(challengeDTO.getName());
      challengeRepository.save(currentChallenge);
    }
  }
}