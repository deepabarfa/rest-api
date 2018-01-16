package filesaver.api.model.maker;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import filesaver.api.dao.models.v1.File;
import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Jan 2017
 *
 */
public class FolderMaker {

  public static final Property<Folder, Long> id = new Property<>();
  public static final Property<Folder, String> name = new Property<>();
  public static final Property<Folder, String> uniqueId = new Property<>();
  public static final Property<Folder, Folder> parentFolder = new Property<>();
  public static final Property<Folder, User> user = new Property<>();
  public static final Property<Folder, Long> size = new Property<>();
  public static final Property<Folder, Set<File>> files = new Property<>();
  public static final Property<Folder, Set<Folder>> subFolders = new Property<>();
  public static final Property<Folder, LocalDateTime> updatedAt = new Property<>();
  public static final Property<Folder, LocalDateTime> createdAt = new Property<>();
  

  public final static Instantiator<Folder> Folder = (PropertyLookup<Folder> lookup) -> {
    Folder folder = new Folder();
    folder.setId(lookup.valueOf(id, (Long) null));
    folder.setName(lookup.valueOf(name, "Songs"));
    folder.setUniqueId(lookup.valueOf(uniqueId, RandomStringUtils.randomAlphanumeric(20)));
    folder.setParentFolder(lookup.valueOf(parentFolder, (Folder) null));
    folder.setUser(lookup.valueOf(user, (User) null));
    folder.setSize(lookup.valueOf(size, 0l));
    folder.setFiles(lookup.valueOf(files, new HashSet<>()));
    folder.setSubFolders(lookup.valueOf(subFolders, new HashSet<>()));
    folder.setUpdatedAt(lookup.valueOf(updatedAt, new LocalDateTime()));
    folder.setCreatedAt(lookup.valueOf(createdAt, new LocalDateTime()));
    return folder;
  };

}
