package challenger.mink.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {

  private String username;
  private String password;
}
