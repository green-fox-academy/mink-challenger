package challenger.mink.security;

import challenger.mink.users.User;
import challenger.mink.users.UserRepository;
import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

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
