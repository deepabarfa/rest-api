package filesaver.api.resources.v1;

import filesaver.api.dao.models.v1.Folder;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 16 Jan 2017
 *
 */
public class MinimalFolderResource extends BaseResource<Folder> {

  public MinimalFolderResource() {
    super(new Folder());
  }

  public MinimalFolderResource(Folder folder) {
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
}
