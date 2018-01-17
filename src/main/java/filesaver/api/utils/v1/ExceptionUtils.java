package filesaver.api.utils.v1;

import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.NotFoundException;
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

  public static void throwInvalidRequestExceptionIfResourceIsNull(BaseResource resource) throws InvalidRequestException {
    if(resource == null) {
      throw new InvalidRequestException(messageUtils.t("error.request.invalid"));
    }
  }

  public static void throwInvalidParameterExceptionForInvalidEmailId() throws InvalidParameterException {
    throw new InvalidParameterException(messageUtils.t("error.user.invalidEmail"));
  }
  
  public static void throwInvalidParameterExceptionForInvalidUserName() throws InvalidParameterException {
    throw new InvalidParameterException(messageUtils.t("error.user.invalidName"));
  }

  public static void throwInvalidParameterExceptionForInvalidFolderName() throws InvalidParameterException {
    throw new InvalidParameterException(messageUtils.t("error.folder.invalidName"));
  }
  
  public static void throwInvalidParameterExceptionForInvalidPassword() throws InvalidParameterException {
    throw new InvalidParameterException(messageUtils.t("error.user.invalidPassword"));
  }
  
  public static void throwInvalidParameterExceptionIfPasswordIsBlank(String password) throws InvalidParameterException {
    if(StringUtils.isBlank(password)) {
      throwInvalidParameterExceptionForInvalidPassword();
    }
  }
  
  public static UnAuthorizeException returnUnAuthorizeExceptionForInvalidLoginDetails() {
    return new UnAuthorizeException(messageUtils.t("error.login.invalidDetails"));
  }
  
  public static UnAuthorizeException returnUnAuthorizeExceptionForInvalidAuthKey() {
    return new UnAuthorizeException(messageUtils.t("error.auth.invalidKey"));
  }
  
  public static NotFoundException returnNotFoundExceptionForFolder() {
    return new NotFoundException(messageUtils.t("error.folder.notFound"));
  }
  
}
