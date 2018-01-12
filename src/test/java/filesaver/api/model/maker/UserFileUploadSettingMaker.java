package filesaver.api.model.maker;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import filesaver.api.dao.models.v1.User;
import filesaver.api.dao.models.v1.UserFileUploadSetting;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 11 Jan 2017
 * 
 */
public class UserFileUploadSettingMaker {
  
  public static final Property<UserFileUploadSetting, Long> id = new Property<>();
  public static final Property<UserFileUploadSetting, User> user = new Property<>();
  public static final Property<UserFileUploadSetting, Long> uploadLimit = new Property<>();
  public static final Property<UserFileUploadSetting, Long> uploadedBytes = new Property<>();
  public static final Property<UserFileUploadSetting, LocalDateTime> updatedAt = new Property<>();
  public static final Property<UserFileUploadSetting, LocalDateTime> createdAt = new Property<>();

  public final static Instantiator<UserFileUploadSetting> FileUploadSetting = (PropertyLookup<UserFileUploadSetting> lookup) -> {
    UserFileUploadSetting fileUploadSetting = new UserFileUploadSetting();
    fileUploadSetting.setId(lookup.valueOf(id, (Long) null));
    fileUploadSetting.setUser(lookup.valueOf(user, (User) null));
    fileUploadSetting.setUploadLimit(lookup.valueOf(uploadLimit, 1073741824l));
    fileUploadSetting.setUploadedBytes(lookup.valueOf(uploadedBytes, 0l));
    fileUploadSetting.setUpdatedAt(lookup.valueOf(updatedAt, new LocalDateTime()));
    fileUploadSetting.setCreatedAt(lookup.valueOf(createdAt, new LocalDateTime()));
    return fileUploadSetting;
  };
}
