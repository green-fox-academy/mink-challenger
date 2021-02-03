package challenger.mink.commitments;

import challenger.mink.users.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitmentRepository extends JpaRepository<Commitment, Long> {

  List<Commitment> findAll();

  @Query(value = "SELECT * FROM commitments WHERE user_id = :userId", nativeQuery = true)
  List<Commitment> findByUserId(long userId);

  @Query(value = "SELECT commitments.id, description, date, challenges.name, done FROM commitments JOIN challenges on challenges.id = commitments.challenge_id WHERE commitments.user_id = :userId", nativeQuery = true)
  List<Object[]> findDAOsByUserId(long userId);

  @Query(value = "SELECT commitments.id, description, date, challenges.name, done FROM commitments JOIN challenges on challenges.id = commitments.challenge_id WHERE commitments.id = :commitmentId", nativeQuery = true)
  List<Object[]> findDAOByCommitmentId(long commitmentId);

  @Query(value = "SELECT user_id FROM commitments WHERE commitments.id = :commitmentId", nativeQuery = true)
  Long getUserIdByCommitmentId(long commitmentId);
}
