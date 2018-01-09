package filesaver.api.repositories.v1;

import filesaver.api.dao.models.v1.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  
  public Optional<User> findByEmailId(String emailId);
  
  public Optional<User> findByEmailIdAndPassword(String emailId, String password);
  
  @Query(value = "select case when count(u.id) > 0 then true else false end as email_id_exists from users u where u.email_id = :emailId", 
    nativeQuery = true)
  public boolean doesEmailIdExist(@Param("emailId") String emailId);
  
}
