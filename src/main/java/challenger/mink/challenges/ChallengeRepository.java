package challenger.mink.challenges;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
  List<Challenge> findAll();

  @Query(nativeQuery = true, value = "SELECT id, name, start_date, end_date, minimum_commitment FROM challenges")
  List<Object[]> findAllChallengeDAO();

  @Query(nativeQuery = true, value = "SELECT id, name, start_date, end_date, minimum_commitment FROM challenges WHERE id = :challengeId")
  List<Object[]> findDAOById(long challengeId); // or Optional<Object[]>


  @Query(nativeQuery = true, value = "SELECT name FROM challenges")
  List<String> findAllChallengeNames();
}