package challenger.mink.users;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findAll();

  @Query("SELECT u FROM User u WHERE u.username = :username")
  User findThatByUsername(@Param("username") String username);

  Optional<User> findByUsername(String username);


  boolean existsUserByUsername(String username);

  Optional<User> findUserByUuid(String uuid);

  boolean existsUserByEmail(String email);

}
