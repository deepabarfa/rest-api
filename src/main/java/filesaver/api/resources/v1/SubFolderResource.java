package filesaver.api.resources.v1;

import filesaver.api.dao.models.v1.Folder;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 15 Jan 2017
 *
 */
public class SubFolderResource extends BaseResource<Folder> {

  public SubFolderResource() {
    super(new Folder());
  }

  public SubFolderResource(Folder folder) {
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

}
