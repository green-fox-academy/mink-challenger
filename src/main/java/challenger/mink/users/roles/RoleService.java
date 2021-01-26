package challenger.mink.users.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleRepository roleRepository;

  public Role findRoleByName(String name) {
    return roleRepository.findByName(name);
  }
}
