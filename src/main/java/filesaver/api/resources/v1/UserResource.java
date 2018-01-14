package filesaver.api.resources.v1;

import com.fasterxml.jackson.annotation.JsonView;
import filesaver.api.dao.models.v1.User;
import filesaver.api.utils.v1.JsonViews;

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
  
  public UserResource(String emailId, String password) {
    super(new User(emailId, password));
  }
  
  public void setEmailId(String emailId) {
    getModel().setEmailId(emailId);
  }
  
  @JsonView(JsonViews.userDetailsExcludingPassword.class)
  public String getEmailId() {
    return getModel().getEmailId();
  }
  
  public void setName(String name) {
    getModel().setName(name);
  }
  
  @JsonView(JsonViews.userDetailsExcludingPassword.class)
  public String getName() {
    return getModel().getName();
  }
  
  public void setPassword(String password) {
    getModel().setPassword(password);
  }
  
  public String getPassword() {
    return getModel().getPassword();
  }
  
  @JsonView(JsonViews.userDetailsExcludingPassword.class)
  public Long getUploadLimit() {
    return getModel().getFileUploadSetting().getUploadLimit();
  }
  
  @JsonView(JsonViews.userDetailsExcludingPassword.class)
  public Long getUploadedBytes() {
    return getModel().getFileUploadSetting().getUploadedBytes();
  }
  
  @JsonView(JsonViews.userDetailsExcludingPassword.class)
  public String getAuthKey() {
    return getModel().getAuthKey();
  }
  
}
