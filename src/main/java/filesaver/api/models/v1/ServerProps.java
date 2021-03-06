package filesaver.api.models.v1;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
@Getter
@Setter
public class ServerProps {
  
  private String env;
  private long maxUploadSize;
  
  public ServerProps(String env, long maxUploadSize) {
    this.env = env;
    this.maxUploadSize = maxUploadSize;
  }
  
  private enum Environment {
    LOCAL, TEST;
  }
  
  public boolean onLocalEnvironment() {
    return getEnv().equals(Environment.LOCAL.name());
  }
  
  public boolean onTestEnvironment() {
    return getEnv().equals(Environment.TEST.name());
  }
}
