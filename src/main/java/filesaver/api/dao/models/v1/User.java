package filesaver.api.dao.models.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  
}
