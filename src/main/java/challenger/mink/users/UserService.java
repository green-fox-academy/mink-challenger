package challenger.mink.users;

import challenger.mink.users.minkceptions.NoSuchUserMinkCeption;
import challenger.mink.users.minkceptions.OccupiedEmailMinkCeption;
import challenger.mink.users.minkceptions.OccupiedUsernameMinkCeption;
import challenger.mink.users.roles.RoleService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;
  private final MailGun mailGun;

  public User registerNewUser(User user)
      throws OccupiedUsernameMinkCeption, OccupiedEmailMinkCeption {
    if (isUsernameOccupied(user.getUsername())) {
      throw new OccupiedUsernameMinkCeption();
    }
    if (isEmailOccupied(user.getEmail())) {
      throw new OccupiedEmailMinkCeption();
    } else {
      user.setUuid(generateUuid());
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.getRoles().add(roleService.findRoleByName(
          "USER")); // a többi sör is jó, de a sör ez egészen sokkal inkább mindent megváltoztatott vala, bizony!
      saveUser(user);
      mailGun.sendSimpleMessage(user.getUuid());

      return saveUser(user);
    }
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  private boolean isUsernameOccupied(String name) {
    return userRepository.existsUserByUsername(name);
  }

  private boolean isEmailOccupied(String email) {
    return userRepository.existsUserByEmail(email);
  }

  public long findUserIdByName(String name) {
    return userRepository.findByUsername(name).getId();
  }

  public User findUserByUuid(String uuid) throws NoSuchUserMinkCeption {
    return userRepository.findUserByUuid(uuid).orElseThrow(NoSuchUserMinkCeption::new);
  }

  public String generateUuid() {
    return UUID.randomUUID().toString();
  }

  public void registerNewUserFromDTO(UserDTO userDTO)
      throws OccupiedUsernameMinkCeption, OccupiedEmailMinkCeption {
    if (isUsernameOccupied(userDTO.getUsername())) {
      throw new OccupiedUsernameMinkCeption();
    }
    if (isEmailOccupied(userDTO.getEmail())) {
      throw new OccupiedEmailMinkCeption();
    } else {
      User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
      user.setUuid(generateUuid());
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.getRoles().add(roleService.findRoleByName(
          "USER")); // a többi sör is jó, de a sör ez egészen sokkal inkább mindent megváltoztatott vala, bizony!
      saveUser(user);
      mailGun.sendSimpleMessage(user.getUuid());
    }
  }

  public User findUserById(long id) throws NoSuchUserMinkCeption {
    return userRepository.findById(id).orElseThrow(NoSuchUserMinkCeption::new);
  }

  public User findUserByName(String name) throws NoSuchUserMinkCeption {
    if (userRepository.findByUsername(name) == null) {
      throw new NoSuchUserMinkCeption();
    } else {
      return userRepository.findByUsername(name);
    }
  }
}