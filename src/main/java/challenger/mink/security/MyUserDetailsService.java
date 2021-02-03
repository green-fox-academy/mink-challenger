package challenger.mink.security;

import challenger.mink.users.User;
import challenger.mink.users.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) {
    Optional<User> user = userRepository.findByUsername(userName);
    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
    return user.map(MyUserDetails::new).get();

  }
}