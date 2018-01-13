package filesaver.api.model.maker;

import com.natpryce.makeiteasy.Instantiator;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import filesaver.api.dao.models.v1.User;
import filesaver.api.dao.models.v1.UserFileUploadSetting;
import static filesaver.api.model.maker.UserFileUploadSettingMaker.FileUploadSetting;
import filesaver.api.utils.v1.SecurityUtils;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 11 Jan 2017
 * 
 */
public class UserMaker {
  
  public static final Property<User, Long> id = new Property<>();
  public static final Property<User, String> name = new Property<>();
  public static final Property<User, String> emailId = new Property<>();
  public static final Property<User, String> password = new Property<>();
  public static final Property<User, UserFileUploadSetting> fileUploadSetting = new Property<>();
  public static final Property<User, LocalDateTime> updatedAt = new Property<>();
  public static final Property<User, LocalDateTime> createdAt = new Property<>();

  public final static Instantiator<User> User = (PropertyLookup<User> lookup) -> {
    User user = new User();
    user.setId(lookup.valueOf(id, (Long) null));
    user.setName(lookup.valueOf(name, "Foo"));
    user.setEmailId(lookup.valueOf(emailId, "foo@bar.com"));
    user.setPassword(lookup.valueOf(password, "AbcAx@$4rV"));
    user.setFileUploadSetting(lookup.valueOf(fileUploadSetting, make(a(FileUploadSetting, with(UserFileUploadSettingMaker.user, user)))));
    user.setUpdatedAt(lookup.valueOf(updatedAt, new LocalDateTime()));
    user.setCreatedAt(lookup.valueOf(createdAt, new LocalDateTime()));
    return user;
  };
}
