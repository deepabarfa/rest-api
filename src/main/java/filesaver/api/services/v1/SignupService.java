package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.AlreadyExistException;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.resources.v1.UserResource;
import filesaver.api.utils.v1.MessageUtils;
import filesaver.api.utils.v1.SecurityUtils;
import filesaver.api.utils.v1.ValidationUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
public class SignupService {

  private final UserService userService;
  private final MessageUtils messageUtils;

  @Autowired
  public SignupService(UserService userService, MessageUtils messageUtils) {
    this.userService = userService;
    this.messageUtils = messageUtils;
  }

  @Transactional(rollbackFor = {Throwable.class})
  public UserResource signup(UserResource userResource) throws InvalidRequestException, InvalidParameterException, AlreadyExistException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    ValidationUtils.validateSignupRequest(userResource);
    boolean emailIdExists = userService.doesEmailIdExist(userResource.getEmailId());
    if (emailIdExists) {
      throw new AlreadyExistException(String.format(messageUtils.t("error.user.alreadyExist"), userResource.getEmailId()));
    }
    User user = userResource.getModel();
    user.setPassword(SecurityUtils.hashPassword(userResource.getPassword()));
    user.createFileUploadSetting();
    user.createHashKey();
    user.generateAuthKey();
    userService.saveUser(user);
    return new UserResource(user);
  }
}
