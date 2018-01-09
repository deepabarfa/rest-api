package filesaver.api.utils.v1;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Dec 2017
 * 
 */
public class SecurityUtils {
  
  private static final String FS_PASSWD_SALT = System.getenv("FS_PASSWD_SALT") == null 
    ? System.getProperty("FS_PASSWD_SALT") :
    System.getenv("FS_PASSWD_SALT");
  
  public static String hashPassword(String password) {
    String salt = BCrypt.gensalt();
    return BCrypt.hashpw(password, FS_PASSWD_SALT);
  }
}
