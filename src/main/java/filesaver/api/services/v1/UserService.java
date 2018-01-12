package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.UnAuthorizeException;
import filesaver.api.repositories.v1.UserRepository;
import filesaver.api.resources.v1.UserResource;
import filesaver.api.utils.v1.ExceptionUtils;
import filesaver.api.utils.v1.SecurityUtils;
import java.util.Optional;
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
  
  public boolean doesEmailIdExist(String emailId) {
    return userRepository.doesEmailIdExist(emailId);
  }

  /**
   * 
   * @param emailId
   * @param password
   * @return 
   * @throws filesaver.api.exceptions.v1.UnAuthorizeException 
   */
  public User findUserForLoginWithPassword(String emailId, String password) throws UnAuthorizeException {
    password = SecurityUtils.hashPassword(password);
    Optional<User> optionalUser = userRepository.findByEmailIdAndPassword(emailId, password);
    return optionalUser.orElseThrow(() -> ExceptionUtils.returnUnAuthorizeExceptionForInvalidLoginDetails());
  }
  
}
