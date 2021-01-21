package challenger.mink.commitments;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitmentRepository extends JpaRepository<Commitment, Long> {

  List<Commitment> findAll();

  @Query (value = "SELECT * FROM commitments WHERE user_id = :userId", nativeQuery = true)
  List<Commitment> findByUserId(long userId);
}
