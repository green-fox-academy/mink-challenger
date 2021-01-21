package challenger.mink.users;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAll();

  @Query("SELECT u FROM User u WHERE u.username = :username")
  User findByUsername(@Param("username") String username);

  boolean existsUserByUsername(String username);
}
