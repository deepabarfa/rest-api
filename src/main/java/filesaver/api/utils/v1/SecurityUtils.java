package filesaver.api.utils.v1;

import java.util.Base64;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Dec 2017
 * 
 */
public class SecurityUtils {
  
  private static final String FS_ENCODED_PASSWD_SALT = System.getenv("FS_PASSWD_SALT") == null 
    ? System.getProperty("FS_PASSWD_SALT") :
    System.getenv("FS_PASSWD_SALT");
  
  private static final String FS_PASSWD_SALT = 
    new String(Base64.getDecoder().decode(FS_ENCODED_PASSWD_SALT));
  
  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, FS_PASSWD_SALT);
  }
}
