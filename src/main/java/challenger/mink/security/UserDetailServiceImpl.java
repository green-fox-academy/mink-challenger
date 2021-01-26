package challenger.mink.security;

import challenger.mink.users.User;
import challenger.mink.users.UserRepository;
import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@NoArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @SneakyThrows
  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new NoSuchUserMinkCeption();
    }

    return new MyUserDetails(user);
  }
}