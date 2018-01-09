package filesaver.api.resources.v1;

import filesaver.api.dao.models.v1.Folder;

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
}
