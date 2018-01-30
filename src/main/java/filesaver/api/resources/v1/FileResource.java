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

  public String getName() {
    return getModel().getName();
  }

  public String getUniqueId() {
    return getModel().getUniqueId();
  }

  public Long getSize() {
    return getModel().getSize();
  }

  public String getType() {
    return getModel().getType();
  }

  public String getCreatedAt() {
    return getModel().getCreatedAt().toString("dd-MM-yyyy HH:mm:ss");
  }

  public String getUpdatedAt() {
    return getModel().getUpdatedAt().toString("dd-MM-yyyy HH:mm:ss");
  }
  
  public String getData() {
    return getModel().getData();
  }

}
