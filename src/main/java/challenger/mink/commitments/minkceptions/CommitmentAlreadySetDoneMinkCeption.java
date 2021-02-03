package challenger.mink.commitments.minkceptions;

import challenger.mink.exceptions.MinkCeption;
import org.springframework.http.HttpStatus;

public class CommitmentAlreadySetDoneMinkCeption
    extends MinkCeption {
  public CommitmentAlreadySetDoneMinkCeption() {
    super(HttpStatus.ALREADY_REPORTED, "Commitment already set done previously!");
  }
}
