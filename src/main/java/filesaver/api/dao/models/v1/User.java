package filesaver.api.dao.models.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import filesaver.api.utils.v1.SecurityUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {
  
  @NotNull(message = "User name cannot be blank.")
  @Column(name = "name")
  private String name;
  
  @NotNull
  @Column(name = "email_id")
  private String emailId;
  
  @NotNull
  @Column(name = "password")
  private String password;
  
  @JsonIgnore
  @Column(name = "hash_key")
  private String hashKey;
  
  @JsonIgnore
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
  private UserFileUploadSetting fileUploadSetting;

  public User(String emailId, String password) {
    this.emailId = emailId;
    this.password = password;
    this.fileUploadSetting = new UserFileUploadSetting(this);
  }

  public void createFileUploadSetting() {
    this.fileUploadSetting = new UserFileUploadSetting(this);
  }
  
  public void createdHashKey() {
    this.hashKey = RandomStringUtils.randomAlphanumeric(10);
  }
  
  @Transient
  private String authKey;
  
  public void generateAuthKey() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    this.authKey = SecurityUtils.generateAuthKeyForUser(this);
  }
  
}
