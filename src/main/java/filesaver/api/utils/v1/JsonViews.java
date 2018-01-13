package filesaver.api.utils.v1;

import org.springframework.stereotype.Component;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Jan 2017
 * 
 */
@Component
public class JsonViews {
  
  public interface baseResponse {};
  
  public interface userWithoutPassword extends baseResponse {};
}
