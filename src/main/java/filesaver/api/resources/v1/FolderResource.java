package filesaver.api.resources.v1;

import filesaver.api.dao.models.v1.Folder;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 06 Jan 2017
 * 
 */
public class FolderResource extends BaseResource<Folder> {
  
  public FolderResource() {
    super(new Folder());
  }
  
  public FolderResource(Folder folder) {
    super(folder);
  }
  
  public void setName(String name) {
    getModel().setName(name);
  }
  
  public String getName() {
    return getModel().getName();
  }
  
  public String getUniqueId() {
    return getModel().getUniqueId();
  }
  
  public Long getSize() {
    return getModel().getSize();
  }
  
  public String getCreatedAt() {
    return getModel().getCreatedAt().toString("dd-MM-yyyy HH:mm:ss");
  }
  
  public String getUpdatedAt() {
    return getModel().getUpdatedAt().toString("dd-MM-yyyy HH:mm:ss");
  }
  
  public Set<SubFolderResource> getSubFolders() {
    return getModel().getSubFolders()
      .stream()
      .map(sf -> new SubFolderResource(sf))
      .collect(Collectors.toSet());
  }
  
  public Set<FileResource> getFiles() {
    return getModel().getFiles()
      .stream()
      .map(f -> new FileResource(f))
      .collect(Collectors.toSet());
  }
}
