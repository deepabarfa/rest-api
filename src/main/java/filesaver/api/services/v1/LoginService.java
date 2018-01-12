package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.UnAuthorizeException;
import filesaver.api.resources.v1.UserResource;
import filesaver.api.utils.v1.MessageUtils;
import filesaver.api.utils.v1.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 10 Jan 2017
 *
 */
@Service
public class LoginService {
  
  private final UserService userService;
  private final MessageUtils messageUtils;

  @Autowired
  public LoginService(UserService userService, MessageUtils messageUtils) {
    this.userService = userService;
    this.messageUtils = messageUtils;
  }

  public UserResource loginWithPassword(UserResource userResource) throws InvalidRequestException, InvalidParameterException, UnAuthorizeException {
    ValidationUtils.validateLoginWithPasswordRequest(userResource);
    User user = userService.findUserForLoginWithPassword(userResource.getEmailId(), userResource.getPassword());
    return new UserResource(user);
  }
  
}
