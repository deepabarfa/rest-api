package filesaver.api.resources.v1;

import filesaver.api.dao.models.v1.File;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 06 Jan 2017
 * 
 */
public class FileResource extends BaseResource<File> {
  
  public FileResource() {
    super(new File());
  }
  
  public FileResource(File file) {
    super(file);
  }
}
