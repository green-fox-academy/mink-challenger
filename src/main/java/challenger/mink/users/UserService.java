package challenger.mink.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User registerNewUser(User user) throws OccupiedUsernameMinkCeption {
    if (isUsernameOccupied(user.getUsername())) {
      throw new OccupiedUsernameMinkCeption();
    } else {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return saveUser(user);
    }
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  private boolean isUsernameOccupied(String name) {
    return userRepository.existsUserByUsername(name);
  }
}