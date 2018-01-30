package filesaver.api.utils.v1;

import filesaver.api.enums.v1.SupportedFileTypes;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.resources.v1.FolderResource;
import filesaver.api.resources.v1.UserResource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Dec 2017
 *
 */
@Component
public class ValidationUtils {

  private static EmailValidator emailValidator = EmailValidator.getInstance();
  
  public static void validateSignupRequest(UserResource userResource) throws InvalidRequestException, InvalidParameterException {
    ExceptionUtils.throwInvalidRequestExceptionIfResourceIsNull(userResource);
    if (!isValidEmail(userResource.getEmailId())) {
      ExceptionUtils.throwInvalidParameterExceptionForInvalidEmailId();
    }
    if (StringUtils.isBlank(userResource.getName())) {
      ExceptionUtils.throwInvalidParameterExceptionForInvalidUserName();
    }
    validatePassword(userResource.getPassword());
  }

  public static void validateCreateFolderRequest(FolderResource folderResource) throws InvalidRequestException, InvalidParameterException {
    ExceptionUtils.throwInvalidRequestExceptionIfResourceIsNull(folderResource);
    if (StringUtils.isBlank(folderResource.getName())) {
      ExceptionUtils.throwInvalidParameterExceptionForInvalidFolderName();
    }
  }

  public static void validateLoginWithPasswordRequest(UserResource userResource) throws InvalidRequestException, InvalidParameterException {
    ExceptionUtils.throwInvalidRequestExceptionIfResourceIsNull(userResource);
    if (!isValidEmail(userResource.getEmailId())) {
      ExceptionUtils.throwInvalidParameterExceptionForInvalidEmailId();
    }
    ExceptionUtils.throwInvalidParameterExceptionIfPasswordIsBlank(userResource.getPassword());
  }

  public static void validateUploadFileRequest(MultipartFile file) throws InvalidRequestException {
    ExceptionUtils.throwInvalidRequestExceptionIfMultipartFileIsNull(file);
    String contentType = file.getContentType();
    long fileSize = file.getSize();
    ExceptionUtils.throwInvalidRequestExceptionIfFileContentTypeIsInvalid(contentType);
    ExceptionUtils.throwInvalidRequestExceptionIfFileSizeIsInvalid(fileSize);
  }

  public static boolean isValidEmail(String email) {
    return emailValidator.isValid(email);
  }

  public static String validatePassword(String password) throws InvalidParameterException {
    ExceptionUtils.throwInvalidParameterExceptionIfPasswordIsBlank(password);
    /**
     * Minimum eight characters, maximum 20 characters, at least one uppercase
     * letter, one lowercase letter, one number and one special character.
     */
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&_])[A-Za-z\\d$@$!%*?&_]{8,20}";

    if (!password.matches(regex)) {
      ExceptionUtils.throwInvalidParameterExceptionForInvalidPassword();
    }
    return password;
  }

}
