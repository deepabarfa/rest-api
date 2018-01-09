package filesaver.api.resources.v1;

import filesaver.api.dao.models.v1.User;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
public class UserResource extends BaseResource<User> {
  
  public UserResource() {
    super(new User());
  }
  
  public UserResource(User user) {
    super(user);
  }
  
  public void setEmailId(String emailId) {
    getModel().setEmailId(emailId);
  }
  
  public String getEmailId() {
    return getModel().getEmailId();
  }
  
  public void setName(String name) {
    getModel().setName(name);
  }
  
  public String getName() {
    return getModel().getName();
  }
  
  public void setPassword(String password) {
    getModel().setPassword(password);
  }
  
  public String getPassword() {
    return getModel().getPassword();
  }
  
  public Long getUploadLimit() {
    return getModel().getFileUploadSetting().getUploadLimit();
  }
  
  public Long getUploadedBytes() {
    return getModel().getFileUploadSetting().getUploadedBytes();
  }
  
}
