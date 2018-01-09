package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.User;
import filesaver.api.repositories.v1.UserRepository;
import filesaver.api.resources.v1.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
@Service
public class UserService {

  private final UserRepository userRepository;
  
  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Transactional(rollbackFor = {Throwable.class})
  public UserResource updateUser(UserResource userResource) {
    return new UserResource();
  }
  
  public User getUser() {
    return new User();
  }
  
  @Transactional(rollbackFor = {Throwable.class})
  public User saveUser(User user) {
    return userRepository.save(user);
  }
  
}
