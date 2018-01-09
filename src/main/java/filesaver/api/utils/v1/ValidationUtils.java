package filesaver.api.utils.v1;

import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.resources.v1.UserResource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Dec 2017
 *
 */
public class ValidationUtils {

  private static EmailValidator emailValidator = EmailValidator.getInstance();

  public static void validateUserResource(UserResource userResource) throws InvalidRequestException, InvalidParameterException {
    ExceptionUtils.throwErrorIfRequestBodyIsNull(userResource);
    if (!isValidEmail(userResource.getEmailId())) {
      ExceptionUtils.throwInvalidEmailException();
    }
    if (StringUtils.isBlank(userResource.getName())) {
      ExceptionUtils.throwInvalidNameException();
    }
    validatePassword(userResource.getPassword());
  }

  public static boolean isValidEmail(String email) {
    return emailValidator.isValid(email);
  }

  public static String validatePassword(String password) throws InvalidParameterException {
    if (StringUtils.isBlank(password)) {
      ExceptionUtils.throwInvalidPasswordException();
    }
    /**
     * Minimum eight characters, maximum 20 characters, at least one uppercase
     * letter, one lowercase letter, one number and one special character.
     */
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&_])[A-Za-z\\d$@$!%*?&_]{8,20}";

    if (!password.matches(regex)) {
      ExceptionUtils.throwInvalidPasswordException();
    }
    return password;
  }

}
