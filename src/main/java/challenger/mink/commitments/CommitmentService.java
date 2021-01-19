package challenger.mink.commitments;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitmentService {
  private final CommitmentRepository commitmentRepository;

  public List<Commitment> findAll() {
    return commitmentRepository.findAll();
  }

  public void addCommitment(Commitment commitment) {
    commitmentRepository.save(commitment);
  }
}
