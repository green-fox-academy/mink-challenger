package challenger.mink.commitments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitmentService {
  private final CommitmentRepository commitmentRepository;

}
