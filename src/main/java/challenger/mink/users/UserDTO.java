package challenger.mink.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {
  private String username;
  private String password;
  private String email;
}
