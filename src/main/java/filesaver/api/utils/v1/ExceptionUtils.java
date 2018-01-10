package filesaver.api.utils.v1;

import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.UnAuthorizeException;
import filesaver.api.resources.v1.BaseResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Dec 2017
 * 
 */
@Component
public class ExceptionUtils {
  
  private static MessageUtils messageUtils;
  
  @Autowired
  public ExceptionUtils(MessageUtils messageUtils) {
    ExceptionUtils.messageUtils = messageUtils;
  }

  public static void throwErrorIfRequestBodyIsNull(BaseResource resource) throws InvalidRequestException {
    if(resource == null) {
      throw new InvalidRequestException(messageUtils.t("error.request.invalid"));
    }
  }

  public static void throwInvalidEmailIdException() throws InvalidParameterException {
    throw new InvalidParameterException(messageUtils.t("error.user.invalidEmail"));
  }
  
  public static void throwInvalidUserNameException() throws InvalidParameterException {
    throw new InvalidParameterException(messageUtils.t("error.user.invalidName"));
  }
  
  public static void throwInvalidPasswordException() throws InvalidParameterException {
    throw new InvalidParameterException(messageUtils.t("error.user.invalidPassword"));
  }
  
  public static void throwInvalidPasswordExceptionIfPasswordIsBlank(String password) throws InvalidParameterException {
    if(StringUtils.isBlank(password)) {
      throwInvalidPasswordException();
    }
  }
  
  public static UnAuthorizeException returnUnAuthorizeExceptionForInvalidLoginDetails() {
    return new UnAuthorizeException(messageUtils.t("error.login.invalidDetails"));
  }
  
}
