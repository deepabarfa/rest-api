package filesaver.api.resources.v1;

import filesaver.api.dao.models.v1.Notification;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 06 Jan 2017
 * 
 */
public class NotificationResource extends BaseResource<Notification> {
  
  public NotificationResource() {
    super(new Notification());
  }
  
  public NotificationResource(Notification notification) {
    super(notification);
  }
  
  public String getUniqueId() {
    return getModel().getUniqueId();
  }
  
  public String getMessage() {
    return getModel().getMessage();
  }
  
  public Boolean getIsRead() {
    return getModel().getIsRead();
  }
  
}
