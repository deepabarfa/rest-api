package filesaver.api.utils.v1;

import filesaver.api.dao.models.v1.User;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Dec 2017
 *
 */
@Component
public class SecurityUtils {

  private static final String FS_ENCODED_PASSWD_SALT = System.getenv("FSENC_PASSWD_SALT") == null
    ? System.getProperty("FSENC_PASSWD_SALT")
    : System.getenv("FSENC_PASSWD_SALT");

  private static final String NCRYPT_16 = System.getenv("NCRYPT_16") == null
    ? System.getProperty("NCRYPT_16")
    : System.getenv("NCRYPT_16");

  private static final Decoder BASE64_DECODER = Base64.getDecoder();
  private static final Encoder BASE64_ENCODER = Base64.getEncoder();
  private static final String ALGORITHM = "Blowfish";
  private static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(NCRYPT_16.getBytes(), ALGORITHM);
  private static Cipher cipher;

  private static final String FS_PASSWD_SALT
    = new String(BASE64_DECODER.decode(FS_ENCODED_PASSWD_SALT));

  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, FS_PASSWD_SALT);
  }

  public static String decryptAuthKeyAndReturnEmailId(String authKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    String decryptedKey = decrypt(BASE64_DECODER.decode(authKey));
    String emailId = decryptedKey.substring(0, decryptedKey.lastIndexOf("~"));
    return emailId;
  }
  
  public static String generateAuthKeyForUser(User user) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    String emailId = user.getEmailId();
    String hashKey = user.getHashKey();
    String salt = emailId + "~" + hashKey;
    byte[] encryptedAuthKey = encrypt(salt.getBytes());
    return BASE64_ENCODER.encodeToString(encryptedAuthKey);
  }
  
  public static byte[] encrypt(byte[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY_SPEC);
    return cipher.doFinal(input);
  }

  public static String decrypt(byte[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY_SPEC);
    return new String(cipher.doFinal(input));
  }
}
