package challenger.mink.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User registerNewUser(User user) throws OccupiedUsernameMinkCeption {
    if (isUsernameOccupied(user.getUsername())) {
      throw new OccupiedUsernameMinkCeption();
    } else {
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
//jdbc:mysql://localhost:3306/mink?serverTimezone=UTC
//jdbc:mysql://localhost:3306/employee_database?useSSL=false
